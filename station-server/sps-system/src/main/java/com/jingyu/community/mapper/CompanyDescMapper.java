package com.jingyu.community.mapper;

import java.util.List;
import com.jingyu.community.domain.CompanyDesc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 单位详情附件Mapper接口
 * 
 * @author jingyu
 * @date 2023-07-12
 */
@Mapper
public interface CompanyDescMapper 
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
    public int insertCompanyDesc(CompanyDesc companyDesc);

    /**
     * 修改单位详情附件
     * 
     * @param companyDesc 单位详情附件
     * @return 结果
     */
    public int updateCompanyDesc(CompanyDesc companyDesc);

    /**
     * 删除单位详情附件
     * 
     * @param id 单位详情附件主键
     * @return 结果
     */
    public int deleteCompanyDescById(Long id);

    /**
     * 批量删除单位详情附件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyDescByIds(Long[] ids);

    void updateCompanyIdById(@Param("id") long id,@Param("companyId")  Long companyId);
}
