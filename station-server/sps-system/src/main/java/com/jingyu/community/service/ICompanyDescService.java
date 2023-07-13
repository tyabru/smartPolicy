package com.jingyu.community.service;

import java.io.IOException;
import java.util.List;
import com.jingyu.community.domain.CompanyDesc;

/**
 * 单位详情附件Service接口
 * 
 * @author jingyu
 * @date 2023-07-12
 */
public interface ICompanyDescService 
{
    /**
     * 查询单位详情附件
     * 
     * @param id 单位详情附件主键
     * @return 单位详情附件
     */
    public CompanyDesc selectCompanyDescById(Long id);

    /**
     * 查询单位详情附件列表
     * 
     * @param companyDesc 单位详情附件
     * @return 单位详情附件集合
     */
    public List<CompanyDesc> selectCompanyDescList(CompanyDesc companyDesc);

    /**
     * 新增单位详情附件
     * 
     * @param companyDesc 单位详情附件
     * @return 结果
     */
    public Long insertCompanyDesc(CompanyDesc companyDesc) throws Exception;

    /**
     * 修改单位详情附件
     * 
     * @param companyDesc 单位详情附件
     * @return 结果
     */
    public Long updateCompanyDesc(CompanyDesc companyDesc) throws Exception;

    /**
     * 批量删除单位详情附件
     * 
     * @param ids 需要删除的单位详情附件主键集合
     * @return 结果
     */
    public int deleteCompanyDescByIds(Long[] ids);

    /**
     * 删除单位详情附件信息
     * 
     * @param id 单位详情附件主键
     * @return 结果
     */
    public int deleteCompanyDescById(Long id, boolean onlyNotDefined);

    void deleteDescByCompanyId(Long id);

    void updateCompanyIdById(long id, Long companyId);
}
