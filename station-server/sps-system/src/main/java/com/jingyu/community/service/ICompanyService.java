package com.jingyu.community.service;

import com.jingyu.community.domain.Company;

import java.util.List;

/**
 * 单位基本信息Service接口
 *
 * @author jingyu
 * @date 2023-07-12
 */
public interface ICompanyService {

    /**
     * 查询单位基本信息
     *
     * @param id 单位基本信息主键
     * @return 单位基本信息
     */
    Company selectCompanyById(Long id);

    /**
     * 查询单位基本信息列表
     *
     * @param company 单位基本信息
     * @return 单位基本信息集合
     */
    List<Company> selectCompanyList(Company company);

    /**
     * 新增单位基本信息
     *
     * @param company 单位基本信息
     * @return 结果
     */
    int insertCompany(Company company);

    /**
     * 修改单位基本信息
     *
     * @param company 单位基本信息
     * @return 结果
     */
    int updateCompany(Company company);

    /**
     * 批量删除单位基本信息
     *
     * @param ids 需要删除的单位基本信息主键集合
     * @return 结果
     */
    int deleteCompanyByIds(Long[] ids);

    /**
     * 删除单位基本信息信息
     *
     * @param id 单位基本信息主键
     * @return 结果
     */
    int deleteCompanyById(Long id);

    boolean codeIsExistsExcludeSelf(Long id, String code);
}
