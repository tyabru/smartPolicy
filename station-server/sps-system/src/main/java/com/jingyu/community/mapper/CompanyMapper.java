package com.jingyu.community.mapper;

import java.util.List;

import com.jingyu.community.domain.Company;
import org.apache.ibatis.annotations.Mapper;

/**
 * 单位基本信息Mapper接口
 * 
 * @author jingyu
 * @date 2023-07-12
 */
@Mapper
public interface CompanyMapper 
{
    /**
     * 查询单位基本信息
     * 
     * @param id 单位基本信息主键
     * @return 单位基本信息
     */
    public Company selectCompanyById(Long id);

    /**
     * 查询单位基本信息列表
     * 
     * @param company 单位基本信息
     * @return 单位基本信息集合
     */
    public List<Company> selectCompanyList(Company company);

    /**
     * 新增单位基本信息
     * 
     * @param company 单位基本信息
     * @return 结果
     */
    public int insertCompany(Company company);

    /**
     * 修改单位基本信息
     * 
     * @param company 单位基本信息
     * @return 结果
     */
    public int updateCompany(Company company);

    /**
     * 删除单位基本信息
     * 
     * @param id 单位基本信息主键
     * @return 结果
     */
    public int deleteCompanyById(Long id);

    /**
     * 批量删除单位基本信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyByIds(Long[] ids);

    int countByCondition(Company company);
}
