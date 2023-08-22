package com.jingyu.safecheck.service.impl;

import java.util.List;

import com.jingyu.safecheck.domain.CheckItemDict;
import com.jingyu.safecheck.mapper.CheckItemDictMapper;
import com.jingyu.safecheck.service.ICheckItemDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安全隐患检查项字典Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-29
 */
@Service
public class CheckItemDictServiceImpl implements ICheckItemDictService
{
    @Autowired
    private CheckItemDictMapper checkItemDictMapper;

    /**
     * 查询安全隐患检查项字典
     * 
     * @param id 安全隐患检查项字典主键
     * @return 安全隐患检查项字典
     */
    @Override
    public CheckItemDict selectCheckItemDictById(Long id)
    {
        return checkItemDictMapper.selectCheckItemDictById(id);
    }

    /**
     * 查询安全隐患检查项字典列表
     * 
     * @param checkItemDict 安全隐患检查项字典
     * @return 安全隐患检查项字典
     */
    @Override
    public List<CheckItemDict> selectCheckItemDictList(CheckItemDict checkItemDict)
    {
        return checkItemDictMapper.selectCheckItemDictList(checkItemDict);
    }

    /**
     * 新增安全隐患检查项字典
     * 
     * @param checkItemDict 安全隐患检查项字典
     * @return 结果
     */
    @Override
    public int insertCheckItemDict(CheckItemDict checkItemDict)
    {
        return checkItemDictMapper.insertCheckItemDict(checkItemDict);
    }

    /**
     * 修改安全隐患检查项字典
     * 
     * @param checkItemDict 安全隐患检查项字典
     * @return 结果
     */
    @Override
    public int updateCheckItemDict(CheckItemDict checkItemDict)
    {
        return checkItemDictMapper.updateCheckItemDict(checkItemDict);
    }

    /**
     * 批量删除安全隐患检查项字典
     * 
     * @param ids 需要删除的安全隐患检查项字典主键
     * @return 结果
     */
    @Override
    public int deleteCheckItemDictByIds(Long[] ids)
    {
        return checkItemDictMapper.deleteCheckItemDictByIds(ids);
    }

    /**
     * 删除安全隐患检查项字典信息
     * 
     * @param id 安全隐患检查项字典主键
     * @return 结果
     */
    @Override
    public int deleteCheckItemDictById(Long id)
    {
        return checkItemDictMapper.deleteCheckItemDictById(id);
    }
}
