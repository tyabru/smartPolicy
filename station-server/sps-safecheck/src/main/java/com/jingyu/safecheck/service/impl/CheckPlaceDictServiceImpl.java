package com.jingyu.safecheck.service.impl;

import java.util.List;

import com.jingyu.safecheck.domain.CheckPlaceDict;
import com.jingyu.safecheck.mapper.CheckPlaceDictMapper;
import com.jingyu.safecheck.service.ICheckPlaceDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安全隐患检查场所字典Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-28
 */
@Service
public class CheckPlaceDictServiceImpl implements ICheckPlaceDictService
{
    @Autowired
    private CheckPlaceDictMapper checkPlaceDictMapper;

    /**
     * 查询安全隐患检查场所字典
     * 
     * @param id 安全隐患检查场所字典主键
     * @return 安全隐患检查场所字典
     */
    @Override
    public CheckPlaceDict selectCheckPlaceDictById(Long id)
    {
        return checkPlaceDictMapper.selectCheckPlaceDictById(id);
    }

    /**
     * 查询安全隐患检查场所字典列表
     * 
     * @param checkPlaceDict 安全隐患检查场所字典
     * @return 安全隐患检查场所字典
     */
    @Override
    public List<CheckPlaceDict> selectCheckPlaceDictList(CheckPlaceDict checkPlaceDict)
    {
        return checkPlaceDictMapper.selectCheckPlaceDictList(checkPlaceDict);
    }

    /**
     * 新增安全隐患检查场所字典
     * 
     * @param checkPlaceDict 安全隐患检查场所字典
     * @return 结果
     */
    @Override
    public int insertCheckPlaceDict(CheckPlaceDict checkPlaceDict)
    {
        return checkPlaceDictMapper.insertCheckPlaceDict(checkPlaceDict);
    }

    /**
     * 修改安全隐患检查场所字典
     * 
     * @param checkPlaceDict 安全隐患检查场所字典
     * @return 结果
     */
    @Override
    public int updateCheckPlaceDict(CheckPlaceDict checkPlaceDict)
    {
        return checkPlaceDictMapper.updateCheckPlaceDict(checkPlaceDict);
    }

    /**
     * 批量删除安全隐患检查场所字典
     * 
     * @param ids 需要删除的安全隐患检查场所字典主键
     * @return 结果
     */
    @Override
    public int deleteCheckPlaceDictByIds(Long[] ids)
    {
        return checkPlaceDictMapper.deleteCheckPlaceDictByIds(ids);
    }

    /**
     * 删除安全隐患检查场所字典信息
     * 
     * @param id 安全隐患检查场所字典主键
     * @return 结果
     */
    @Override
    public int deleteCheckPlaceDictById(Long id)
    {
        return checkPlaceDictMapper.deleteCheckPlaceDictById(id);
    }
}
