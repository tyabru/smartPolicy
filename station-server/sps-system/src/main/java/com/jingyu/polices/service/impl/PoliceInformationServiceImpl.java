package com.jingyu.polices.service.impl;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jingyu.common.core.domain.entity.SysUser;
import com.jingyu.common.exception.CustomException;
import com.jingyu.common.utils.*;
import com.jingyu.common.utils.sign.AESUtil;
import com.jingyu.common.utils.sign.Md5Utils;
import com.jingyu.polices.domain.PoliceCars;
import com.jingyu.polices.domain.PoliceDutyGroups;
import com.jingyu.polices.mapper.PoliceDutyGroupsMapper;
import com.jingyu.system.domain.SysUserPost;
import com.jingyu.system.domain.SysUserRole;
import com.jingyu.system.mapper.SysUserMapper;
import com.jingyu.system.mapper.SysUserPostMapper;
import com.jingyu.system.mapper.SysUserRoleMapper;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.jingyu.polices.mapper.PoliceInformationMapper;
import com.jingyu.polices.domain.PoliceInformation;
import com.jingyu.polices.service.IPoliceInformationService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 警员基本信息Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Service
public class PoliceInformationServiceImpl implements IPoliceInformationService
{
    @Value("${jingyu.profile}")
    private String filePath;

    @Resource
    private PoliceInformationMapper policeInformationMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private PoliceDutyGroupsMapper policeDutyGroupsMapper;

    /**
     * 查询警员基本信息
     * 
     * @param id 警员基本信息主键
     * @return 警员基本信息
     */
    @Override
    public PoliceInformation selectPoliceInformationById(Long id)
    {
        return policeInformationMapper.selectPoliceInformationById(id);
    }

    /**
     * 查询警员基本信息列表
     * 
     * @param policeInformation 警员基本信息
     * @return 警员基本信息
     */
    @Override
    public List<PoliceInformation> selectPoliceInformationList(PoliceInformation policeInformation)
    {
        return policeInformationMapper.selectPoliceInformationList(policeInformation);
    }

    /**
     * 新增警员基本信息
     * 
     * @param policeInformation 警员基本信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertPoliceInformation(PoliceInformation policeInformation)
    {
        policeInformation.setBirthday(StringReplaceUtil.idCardToBirthday(AESUtil.decrypt(policeInformation.getIdCard())));
        //校验执法记录仪和身份证号码是否重复
        handleData(policeInformation);
        //回填用户user_id
        SysUser sysUser = sysUserMapper.selectUserByUserName(policeInformation.getPoliceNumber());
        policeInformation.setUserId(sysUser.getUserId());
        return policeInformationMapper.insertPoliceInformation(policeInformation);
    }

    /**
     * 修改警员基本信息
     * 
     * @param policeInformation 警员基本信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updatePoliceInformation(PoliceInformation policeInformation)
    {
        policeInformation.setBirthday(StringReplaceUtil.idCardToBirthday(AESUtil.decrypt(policeInformation.getIdCard())));
        //校验执法记录仪和身份证号码是否重复
        handleData(policeInformation);
        return policeInformationMapper.updatePoliceInformation(policeInformation);
    }

    /**
     * 校验执法记录仪和身份证号码是否重复
     * */
    public void handleData(PoliceInformation policeInformation) {
        //校验身份证号码
        PoliceInformation police = policeInformationMapper.selectPoliceInformationByIdCard(policeInformation.getIdCard());
        if (policeInformation.getUserId() != 0 && StringUtils.isNotNull(police)
                && policeInformation.getUserId().longValue() != police.getUserId().longValue()) {
            throw new CustomException("该身份证号码已存在，请核对身份证是否正确！");
        } else if (policeInformation.getUserId() == null && StringUtils.isNotNull(police)){
            throw new CustomException("该身份证号码已存在，请核对身份证是否正确！");
        }
        //校验执法记录仪编码
        if (policeInformation.getIsVehicle().equals("Y")) {
            PoliceInformation policeInfo = policeInformationMapper.getPoliceInformationByEquipmentNumber(policeInformation.getEquipmentNumber());
            if (policeInformation.getUserId() != 0 && StringUtils.isNotNull(policeInfo)
                    && policeInfo.getUserId().longValue() != policeInformation.getUserId().longValue()) {
                throw new CustomException("执法记录仪编码" + policeInfo.getEquipmentNumber() + "与警号" + policeInfo.getPoliceNumber() + "绑定,请重新选择！");
            } else if (policeInformation.getUserId() == null && StringUtils.isNotNull(policeInfo)) {
                throw new CustomException("执法记录仪编码" + policeInfo.getEquipmentNumber() + "与警号" + policeInfo.getPoliceNumber() + "绑定,请重新选择！");
            }
        } else {
            policeInformation.setEquipmentNumber("null");
        }
        //删除警员照片
        if (policeInformation.getUserId() != 0) {
            PoliceInformation policeInformation1 = policeInformationMapper.selectPoliceInformationByuserId(policeInformation.getUserId());
            if (!policeInformation.getPolicePhoto().equals(policeInformation1.getPolicePhoto())) {
                deletePolicePhoto(policeInformation1.getUserId());
            }
        }
    }

    /**
     * 批量删除警员基本信息
     * 
     * @param ids 需要删除的警员基本信息主键
     * @return 结果
     */
    @Override
    public int deletePoliceInformationByIds(Long[] ids)
    {
        for (Long id : ids) {
            //停用警员账号
            deactivatePoliceInformation(id);
            //删除警员分组表中的警员
            updatePoliceDutyGroupTeamMembers(id);
        }
        return policeInformationMapper.deletePoliceInformationByIds(ids);
    }

    /**
     * 删除警员基本信息信息
     * 
     * @param id 警员基本信息主键
     * @return 结果
     */
    @Override
    public int deletePoliceInformationById(Long id)
    {
        //停用警员账号
        deactivatePoliceInformation(id);
        updatePoliceDutyGroupTeamMembers(id);
        return policeInformationMapper.deletePoliceInformationById(id);
    }

    /**
     * 查询警员基本信息列表
     *
     * @param idCard 身份证号码
     * @return 警员基本信息
     */
    @Override
    public PoliceInformation selectPoliceInformationByIdCard(String idCard) {
        return policeInformationMapper.selectPoliceInformationByIdCard(idCard);
    }

    /**
     * 查询警员基本信息列表
     *
     * @param userId 用户ID
     * @return 警员基本信息
     */
    @Override
    public PoliceInformation selectPoliceInformationByUserId(Long userId) {
        return policeInformationMapper.selectPoliceInformationByuserId(userId);
    }

    /**
     * 批量删除警员基本信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    public int deletePoliceInformationByUserIds(Long[] userIds) {
        //删除警员照片
        for (Long userId : userIds) {
            deletePolicePhoto(userId);
        }
       return policeInformationMapper.deletePoliceInformationByUserIds(userIds);
    }

    /**
     * 停用该警员的账号
     * */
    public int deactivatePoliceInformation(Long id)
    {
        SysUser sysUser = new SysUser();
        PoliceInformation policeInformation = policeInformationMapper.selectPoliceInformationById(id);
        if (StringUtils.isNotNull(policeInformation)) {
            sysUser.setStatus("1");
            sysUser.setUserId(policeInformation.getUserId());
        }
        return sysUserMapper.updateUser(sysUser);
    }

    /**
     * 删除警员分组表中的警员
     * */
    public void updatePoliceDutyGroupTeamMembers(Long id)
    {
        PoliceDutyGroups policeDutyGroups = new PoliceDutyGroups();
        PoliceInformation policeInformation = policeInformationMapper.selectPoliceInformationById(id);
        if (StringUtils.isNotNull(policeInformation)) {
            policeDutyGroups.setTeamMembers(policeInformation.getPoliceNumber());
        }
        List<PoliceDutyGroups> list = policeDutyGroupsMapper.selectPoliceDutyGroupsList(policeDutyGroups);
        for (PoliceDutyGroups dutyGroups : list) {
            dutyGroups.setTeamMembers(dutyGroups.getTeamMembers().replace(policeInformation.getPoliceNumber() + ",",""));
            policeDutyGroupsMapper.updatePoliceDutyGroups(dutyGroups);
        }
    }

    /**
     * 删除警员照片
     * */
    public void deletePolicePhoto (Long userId) {
        PoliceInformation policeInformation = policeInformationMapper.selectPoliceInformationByuserId(userId);
        String fileUrlPath = filePath + policeInformation.getPolicePhoto();
        File file = new File(fileUrlPath);
        if (file.exists()) {
            file.delete();
        } else {
            throw new ClassCastException("警员照片不存在,请确定照片路径是否正确!");
        }
    }
}
