package com.jingyu.person.service;

import java.util.List;
import com.jingyu.person.domain.CompanyEmployee;

/**
 * 单位员工Service接口
 * 
 * @author jingyu
 * @date 2023-07-21
 */
public interface ICompanyEmployeeService 
{
    /**
     * 查询单位员工
     * 
     * @param id 单位员工主键
     * @return 单位员工
     */
    public CompanyEmployee selectCompanyEmployeeById(Long id);

    /**
     * 查询单位员工列表
     * 
     * @param companyEmployee 单位员工
     * @return 单位员工集合
     */
    public List<CompanyEmployee> selectCompanyEmployeeList(CompanyEmployee companyEmployee);

    /**
     * 新增单位员工
     * 
     * @param companyEmployee 单位员工
     * @return 结果
     */
    public int insertCompanyEmployee(CompanyEmployee companyEmployee);

    /**
     * 修改单位员工
     * 
     * @param companyEmployee 单位员工
     * @return 结果
     */
    public int updateCompanyEmployee(CompanyEmployee companyEmployee);

    /**
     * 批量删除单位员工
     * 
     * @param ids 需要删除的单位员工主键集合
     * @return 结果
     */
    public int deleteCompanyEmployeeByIds(Long[] ids);

    /**
     * 删除单位员工信息
     * 
     * @param id 单位员工主键
     * @return 结果
     */
    public int deleteCompanyEmployeeById(Long id);
}
