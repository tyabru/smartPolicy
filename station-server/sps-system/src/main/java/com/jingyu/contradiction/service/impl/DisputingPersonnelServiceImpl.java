package com.jingyu.contradiction.service.impl;

import com.jingyu.common.exception.CustomException;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.encryption_decryption.SensitiveNewsHander;
import com.jingyu.common.utils.file.FileUtils;
import com.jingyu.common.utils.sign.AESUtil;
import com.jingyu.contradiction.domain.DisputingPersonnel;
import com.jingyu.contradiction.mapper.DisputingPersonnelMapper;
import com.jingyu.contradiction.service.IDisputingPersonnelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 参与人员信息Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
@Service
public class DisputingPersonnelServiceImpl implements IDisputingPersonnelService 
{
    @Resource
    private DisputingPersonnelMapper disputingPersonnelMapper;

    /**
     * 查询参与人员信息
     * 
     * @param id 参与人员信息主键
     * @return 参与人员信息
     */
    @Override
    public DisputingPersonnel selectDisputingPersonnelById(Long id)
    {
        return disputingPersonnelMapper.selectDisputingPersonnelById(id);
    }

    /**
     * 查询参与人员信息列表
     * 
     * @param disputingPersonnel 参与人员信息
     * @return 参与人员信息
     */
    @Override
    public List<DisputingPersonnel> selectDisputingPersonnelList(DisputingPersonnel disputingPersonnel)
    {
        return disputingPersonnelMapper.selectDisputingPersonnelList(disputingPersonnel);
    }

    /**
     * 新增参与人员信息
     * 
     * @param disputingPersonnel 参与人员信息
     * @return 结果
     */
    @Override
    public int insertDisputingPersonnel(DisputingPersonnel disputingPersonnel)
    {
        SensitiveNewsHander.revertEncryptAttrs(disputingPersonnel);
        List<DisputingPersonnel> list = getDisputingPersonnelList(disputingPersonnel);
        if (StringUtils.isNotNull(list) && list.size() > 0) {
            throw new CustomException("该人员信息在该案件中已存在,请勿重复添加！");
        }
        return disputingPersonnelMapper.insertDisputingPersonnel(disputingPersonnel);
    }

    /**
     * 修改参与人员信息
     * 
     * @param disputingPersonnel 参与人员信息
     * @return 结果
     */
    @Override
    public int updateDisputingPersonnel(DisputingPersonnel disputingPersonnel)
    {
        DisputingPersonnel old = selectDisputingPersonnelById(disputingPersonnel.getId());
        old.setCerNo(AESUtil.decrypt(old.getCerNo()));
        old.setPhone(AESUtil.decrypt(old.getPhone()));
        SensitiveNewsHander.revertNotEditAttrs(disputingPersonnel,old);
        SensitiveNewsHander.revertEncryptAttrs(disputingPersonnel);
        //判重
        if (disputingPersonnel.getId() != old.getId()) {
            List<DisputingPersonnel> list = getDisputingPersonnelList(disputingPersonnel);
            if (list.size() > 0) {
                throw new CustomException("该人员信息在该案件中已存在,请勿重复添加！");
            }
        }
        boolean needDelOldFile = !Objects.equals(disputingPersonnel.getImageUrl(), old.getImageUrl());
        int i = disputingPersonnelMapper.updateDisputingPersonnel(disputingPersonnel);
        if(i > 0) {
            if(needDelOldFile) {
                FileUtils.deleteFile(old.getImageUrl());
            }
        }
        return i;
    }

    public List<DisputingPersonnel> getDisputingPersonnelList(DisputingPersonnel disputingPersonnel) {
        DisputingPersonnel disputingPersonnel1 = new DisputingPersonnel();
        disputingPersonnel1.setCaseCode(disputingPersonnel.getCaseCode());
        disputingPersonnel1.setCerNo(disputingPersonnel.getCerNo());
        return selectDisputingPersonnelList(disputingPersonnel1);
    }

    /**
     * 批量删除参与人员信息
     * 
     * @param ids 需要删除的参与人员信息主键
     * @return 结果
     */
    @Override
    public int deleteDisputingPersonnelByIds(Long[] ids)
    {
        return disputingPersonnelMapper.deleteDisputingPersonnelByIds(ids);
    }

    /**
     * 删除参与人员信息信息
     * 
     * @param id 参与人员信息主键
     * @return 结果
     */
    @Override
    public int deleteDisputingPersonnelById(Long id)
    {
        return disputingPersonnelMapper.deleteDisputingPersonnelById(id);
    }

    /**
     * 查询参与人员信息
     *
     * @param caseCode 案件编码
     * @return 参与人员信息
     */
    @Override
    public List<DisputingPersonnel> getContradictionPersonByCaseCode(String caseCode) {
        return disputingPersonnelMapper.getContradictionPersonByCaseCode(caseCode);
    }
}
