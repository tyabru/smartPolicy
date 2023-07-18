package com.jingyu.polices.service.impl;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jingyu.common.core.domain.entity.SysUser;
import com.jingyu.common.utils.*;
import com.jingyu.common.utils.sign.AESUtil;
import com.jingyu.common.utils.sign.Md5Utils;
import com.jingyu.system.domain.SysUserPost;
import com.jingyu.system.domain.SysUserRole;
import com.jingyu.system.mapper.SysUserMapper;
import com.jingyu.system.mapper.SysUserPostMapper;
import com.jingyu.system.mapper.SysUserRoleMapper;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.polices.mapper.PoliceInformationMapper;
import com.jingyu.polices.domain.PoliceInformation;
import com.jingyu.polices.service.IPoliceInformationService;

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
    @Resource
    private PoliceInformationMapper policeInformationMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Resource
    private SysUserPostMapper userPostMapper;

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
    public int insertPoliceInformation(PoliceInformation policeInformation)
    {
        policeInformation.setBirthday(StringReplaceUtil.idCardToBirthday(AESUtil.decrypt(policeInformation.getIdCard())));
        //校验重复
        PoliceInformation police = policeInformationMapper.selectPoliceInformationByPoliceNumber(policeInformation.getPoliceNumber());
//        if (StringUtils.isNull(police)) {
//            throw new CustomException("APPKEY错误，请核对APPKEY是否正确！");
//        }
        int i = policeInformationMapper.insertPoliceInformation(policeInformation);
        if (i > 0) {
            //添加用户
            addSysUser(policeInformation);
        }
        return i;
    }

    /**
     * 添加用户信息
     * */
    public void addSysUser(PoliceInformation policeInformation) {
        SysUser sysUser = new SysUser();
        sysUser.setDeptId(Long.parseLong(policeInformation.getDeptId()));
        sysUser.setUserName(AESUtil.decrypt(policeInformation.getPhoneNumber()));
        sysUser.setNickName(policeInformation.getPoliceName());
        sysUser.setPhonenumber(AESUtil.decrypt(policeInformation.getPhoneNumber()));
        sysUser.setSex(policeInformation.getGender().toString());
        sysUser.setDelFlag("0");
        sysUser.setStatus("0");
        sysUser.setCreateBy(policeInformation.getOperateName());
        sysUser.setCreateTime(new Date());
        String passWard = "Abc" + sysUser.getPhonenumber().substring(sysUser.getPhonenumber().length()-6);
        sysUser.setPassword(SecurityUtils.encryptPassword(passWard));
        Long[] roleIds = new Long[1];
        roleIds[0] = Long.parseLong("100");//警员
        sysUser.setRoleIds(roleIds);
        Long[] postIds = new Long[1];
        postIds[0] = policeInformation.getPoliceType();
        sysUser.setPostIds(postIds);
        int i = sysUserMapper.insertUser(sysUser);
        if (i > 0) {
            SysUser sysUser1 = sysUserMapper.selectUserByUserName(sysUser.getUserName());
            PoliceInformation policeInformation1 = new PoliceInformation();
            policeInformation1.setUserId(sysUser1.getUserId());
            policeInformationMapper.updatePoliceInformation(policeInformation1);
        }
        // 新增用户岗位关联
        insertUserPost(sysUser);
        // 新增用户与角色管理
        insertUserRole(sysUser);
    }

    public void insertUserPost(SysUser user) {
        Long[] posts = user.getPostIds();
        if (StringUtils.isNotEmpty(posts)) {
            // 新增用户与岗位管理
            List<SysUserPost> list = new ArrayList<SysUserPost>(posts.length);
            for (Long postId : posts) {
                SysUserPost up = new SysUserPost();
                up.setUserId(user.getUserId());
                up.setPostId(postId);
                list.add(up);
            }
            userPostMapper.batchUserPost(list);
        }
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        if (StringUtils.isNotEmpty(user.getRoleIds())) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>(user.getRoleIds().length);
            for (Long roleId : user.getRoleIds()) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            userRoleMapper.batchUserRole(list);
        }
    }

    /**
     * 修改警员基本信息
     * 
     * @param policeInformation 警员基本信息
     * @return 结果
     */
    @Override
    public int updatePoliceInformation(PoliceInformation policeInformation)
    {
        policeInformation.setBirthday(StringReplaceUtil.idCardToBirthday(AESUtil.decrypt(policeInformation.getIdCard())));
        return policeInformationMapper.updatePoliceInformation(policeInformation);
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
        return policeInformationMapper.deletePoliceInformationById(id);
    }
}
