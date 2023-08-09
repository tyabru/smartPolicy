package com.jingyu.person.service.impl;

import java.util.List;

import com.jingyu.common.exception.CustomException;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.encryption_decryption.SensitiveNewsHander;
import com.jingyu.common.utils.sign.AESUtil;
import com.jingyu.community.domain.CommunityWuye;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.person.mapper.CompanyEmployeeMapper;
import com.jingyu.person.domain.CompanyEmployee;
import com.jingyu.person.service.ICompanyEmployeeService;

import javax.annotation.Resource;

/**
 * 单位员工Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-21
 */
@Service
public class CompanyEmployeeServiceImpl implements ICompanyEmployeeService 
{
    @Resource
    private CompanyEmployeeMapper companyEmployeeMapper;

    /**
     * 查询单位员工
     * 
     * @param id 单位员工主键
     * @return 单位员工
     */
    @Override
    public CompanyEmployee selectCompanyEmployeeById(Long id)
    {
        return companyEmployeeMapper.selectCompanyEmployeeById(id);
    }

    /**
     * 查询单位员工列表
     * 
     * @param companyEmployee 单位员工
     * @return 单位员工
     */
    @Override
    public List<CompanyEmployee> selectCompanyEmployeeList(CompanyEmployee companyEmployee)
    {
        return companyEmployeeMapper.selectCompanyEmployeeList(companyEmployee);
    }

    /**
     * 新增单位员工
     * 
     * @param companyEmployee 单位员工
     * @return 结果
     */
    @Override
    public int insertCompanyEmployee(CompanyEmployee companyEmployee)
    {
        companyEmployee.setCreateTime(DateUtils.getNowDate());
        //判重
        List<CompanyEmployee> companyEmployeeList = getCompanyEmployeeList(companyEmployee);
        if (companyEmployeeList.size() > 0) {
            throw new CustomException("该人员信息在单位中已存在,请勿重复添加！");
        }
        return companyEmployeeMapper.insertCompanyEmployee(companyEmployee);
    }

    /**
     * 修改单位员工
     * 
     * @param companyEmployee 单位员工
     * @return 结果
     */
    @Override
    public int updateCompanyEmployee(CompanyEmployee companyEmployee)
    {
        companyEmployee.setUpdateTime(DateUtils.getNowDate());
        CompanyEmployee old = selectCompanyEmployeeById(companyEmployee.getId());
        old.setIdentityCode(AESUtil.decrypt(old.getIdentityCode()));
        old.setPhone(AESUtil.decrypt(old.getPhone()));
        SensitiveNewsHander.revertNotEditAttrs(companyEmployee,old);
        SensitiveNewsHander.revertEncryptAttrs(companyEmployee);
        //判重
        if (companyEmployee.getCompanyId() != old.getCompanyId()) {
            List<CompanyEmployee> companyEmployeeList = getCompanyEmployeeList(companyEmployee);
            if (companyEmployeeList.size() > 0) {
                throw new CustomException("该人员信息在单位中已存在,请勿重复添加！");
            }
        }
        return companyEmployeeMapper.updateCompanyEmployee(companyEmployee);
    }

    /**
     * 根据单位ID和身份证号码查询
     * */
    public List<CompanyEmployee> getCompanyEmployeeList(CompanyEmployee companyEmployee) {
        CompanyEmployee companyEmployee1 = new CompanyEmployee();
        companyEmployee1.setCompanyId(companyEmployee.getCompanyId());
        companyEmployee1.setIdentityCode(companyEmployee.getIdentityCode());
        List<CompanyEmployee> list = companyEmployeeMapper.selectCompanyEmployeeList(companyEmployee1);
        return list;
    }

    /**
     * 批量删除单位员工
     * 
     * @param ids 需要删除的单位员工主键
     * @return 结果
     */
    @Override
    public int deleteCompanyEmployeeByIds(Long[] ids)
    {
        return companyEmployeeMapper.deleteCompanyEmployeeByIds(ids);
    }

    /**
     * 删除单位员工信息
     * 
     * @param id 单位员工主键
     * @return 结果
     */
    @Override
    public int deleteCompanyEmployeeById(Long id)
    {
        return companyEmployeeMapper.deleteCompanyEmployeeById(id);
    }
}
