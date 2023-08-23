package com.jingyu.safecheck.mapper;

import java.util.List;

import com.jingyu.safecheck.domain.CheckPlaceDict;

/**
 * 安全隐患检查场所字典Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-28
 */
public interface CheckPlaceDictMapper 
{
    /**
     * 查询安全隐患检查场所字典
     * 
     * @param id 安全隐患检查场所字典主键
     * @return 安全隐患检查场所字典
     */
    public CheckPlaceDict selectCheckPlaceDictById(Long id);

    /**
     * 查询安全隐患检查场所字典列表
     * 
     * @param checkPlaceDict 安全隐患检查场所字典
     * @return 安全隐患检查场所字典集合
     */
    public List<CheckPlaceDict> selectCheckPlaceDictList(CheckPlaceDict checkPlaceDict);

    /**
     * 新增安全隐患检查场所字典
     * 
     * @param checkPlaceDict 安全隐患检查场所字典
     * @return 结果
     */
    public int insertCheckPlaceDict(CheckPlaceDict checkPlaceDict);

    /**
     * 修改安全隐患检查场所字典
     * 
     * @param checkPlaceDict 安全隐患检查场所字典
     * @return 结果
     */
    public int updateCheckPlaceDict(CheckPlaceDict checkPlaceDict);

    /**
     * 删除安全隐患检查场所字典
     * 
     * @param id 安全隐患检查场所字典主键
     * @return 结果
     */
    public int deleteCheckPlaceDictById(Long id);

    /**
     * 批量删除安全隐患检查场所字典
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCheckPlaceDictByIds(Long[] ids);
}
