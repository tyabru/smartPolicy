package com.jingyu.safecheck.service;

import java.util.List;

import com.jingyu.safecheck.domain.CheckItemDict;

/**
 * 安全隐患检查项字典Service接口
 * 
 * @author ruoyi
 * @date 2023-06-29
 */
public interface ICheckItemDictService 
{
    /**
     * 查询安全隐患检查项字典
     * 
     * @param id 安全隐患检查项字典主键
     * @return 安全隐患检查项字典
     */
    public CheckItemDict selectCheckItemDictById(Long id);

    /**
     * 查询安全隐患检查项字典列表
     * 
     * @param checkItemDict 安全隐患检查项字典
     * @return 安全隐患检查项字典集合
     */
    public List<CheckItemDict> selectCheckItemDictList(CheckItemDict checkItemDict);

    /**
     * 新增安全隐患检查项字典
     * 
     * @param checkItemDict 安全隐患检查项字典
     * @return 结果
     */
    public int insertCheckItemDict(CheckItemDict checkItemDict);

    /**
     * 修改安全隐患检查项字典
     * 
     * @param checkItemDict 安全隐患检查项字典
     * @return 结果
     */
    public int updateCheckItemDict(CheckItemDict checkItemDict);

    /**
     * 批量删除安全隐患检查项字典
     * 
     * @param ids 需要删除的安全隐患检查项字典主键集合
     * @return 结果
     */
    public int deleteCheckItemDictByIds(Long[] ids);

    /**
     * 删除安全隐患检查项字典信息
     * 
     * @param id 安全隐患检查项字典主键
     * @return 结果
     */
    public int deleteCheckItemDictById(Long id);
}
