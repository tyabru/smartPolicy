package com.jingyu.polices.mapper;

import java.util.List;
import com.jingyu.polices.domain.PoliceFileManagements;

/**
 * 日常文件管理Mapper接口
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
public interface PoliceFileManagementsMapper 
{
    /**
     * 查询日常文件管理
     * 
     * @param id 日常文件管理主键
     * @return 日常文件管理
     */
    public PoliceFileManagements selectPoliceFileManagementsById(Long id);

    /**
     * 查询日常文件管理列表
     * 
     * @param policeFileManagements 日常文件管理
     * @return 日常文件管理集合
     */
    public List<PoliceFileManagements> selectPoliceFileManagementsList(PoliceFileManagements policeFileManagements);

    /**
     * 新增日常文件管理
     * 
     * @param policeFileManagements 日常文件管理
     * @return 结果
     */
    public int insertPoliceFileManagements(PoliceFileManagements policeFileManagements);

    /**
     * 修改日常文件管理
     * 
     * @param policeFileManagements 日常文件管理
     * @return 结果
     */
    public int updatePoliceFileManagements(PoliceFileManagements policeFileManagements);

    /**
     * 删除日常文件管理
     * 
     * @param id 日常文件管理主键
     * @return 结果
     */
    public int deletePoliceFileManagementsById(Long id);

    /**
     * 批量删除日常文件管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePoliceFileManagementsByIds(Long[] ids);
}
