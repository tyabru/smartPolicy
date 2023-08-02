package com.jingyu.person.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.person.mapper.CompanyEmployeeMapper;
import com.jingyu.person.domain.CompanyEmployee;
import com.jingyu.person.service.ICompanyEmployeeService;

/**
 * 单位员工Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-21
 */
@Service
public class CompanyEmployeeServiceImpl implements ICompanyEmployeeService 
{
    @Autowired
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
        return companyEmployeeMapper.updateCompanyEmployee(companyEmployee);
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
