package com.jingyu.system.service.impl;

import java.util.List;

import com.github.pagehelper.util.StringUtil;
import com.jingyu.common.core.domain.entity.SysDictType;
import com.jingyu.common.exception.base.BaseException;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.system.mapper.SysDictTypeMapper;
import com.jingyu.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.common.core.domain.entity.SysDictData;
import com.jingyu.common.utils.DictUtils;
import com.jingyu.system.mapper.SysDictDataMapper;
import com.jingyu.system.service.ISysDictDataService;

/**
 * 字典 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService
{
    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Autowired
    private ISysDictTypeService dictTypeService;

    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData)
    {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue)
    {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode)
    {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据信息
     * 
     * @param dictCodes 需要删除的字典数据ID
     */
    @Override
    public void deleteDictDataByIds(Long[] dictCodes)
    {
        for (Long dictCode : dictCodes)
        {
            SysDictData data = selectDictDataById(dictCode);
            dictDataMapper.deleteDictDataById(dictCode);
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
    }

    /**
     * 新增保存字典数据信息
     * 
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData data)
    {
        int row = dictDataMapper.insertDictData(data);
        if (row > 0)
        {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     * 
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData data)
    {
        int row = dictDataMapper.updateDictData(data);
        if (row > 0)
        {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }

    @Override
    public synchronized void checkDictIsExists(String dictType, String key, String typeLabel, String label) {
        SysDictType dictTypeItem = dictTypeService.selectDictTypeByType(dictType);
        if(dictTypeItem == null || dictTypeItem.getDictId() == null) {
            if(StringUtils.isEmpty(typeLabel)) {
                throw new BaseException("缺失字典项【"+dictType+"】, 无法执行新增字典项。");
            } else {
                dictTypeItem = new SysDictType();
                dictTypeItem.setDictName(typeLabel);
                dictTypeItem.setDictType(dictType);
                int i = dictTypeService.insertDictType(dictTypeItem);
            }

        }
        if(StringUtils.isNotEmpty(key)) {
            String dictLabel = dictDataMapper.selectDictLabel(dictType, key);
            if(StringUtils.isEmpty(dictLabel)) {
                SysDictData sysDictData = new SysDictData();
                sysDictData.setDictType(dictType);
                sysDictData.setDictSort(0L);
                sysDictData.setDictValue(key);
                sysDictData.setListClass("default");
                sysDictData.setDictLabel(StringUtils.isEmpty(label)? key: label);
                this.insertDictData(sysDictData);
            }
        }
    }
}
