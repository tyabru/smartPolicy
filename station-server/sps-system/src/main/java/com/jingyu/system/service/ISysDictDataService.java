package com.jingyu.system.service;

import java.util.List;
import com.jingyu.common.core.domain.entity.SysDictData;

/**
 * 字典 业务层
 * 
 * @author ruoyi
 */
public interface ISysDictDataService
{
    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    public List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String selectDictLabel(String dictType, String dictValue);

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    public SysDictData selectDictDataById(Long dictCode);

    /**
     * 批量删除字典数据信息
     * 
     * @param dictCodes 需要删除的字典数据ID
     */
    public void deleteDictDataByIds(Long[] dictCodes);

    /**
     * 新增保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int insertDictData(SysDictData dictData);

    /**
     * 修改保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int updateDictData(SysDictData dictData);
<<<<<<< HEAD

    /**
     * 根据条件分页查询字典数据
     * 如果没有【dictType】这个字典则抛出异常
     * 如果没有【key】这个字典键值对则添加进数据库{ value: key, label: label}
     *
     * @param dictType 字典类型
     * @param key 字典的键值
     * @param defaultTypeLabel 如果字典不存在时，新增字典时用到的字典名称
     * @param label 如果字典不存在时，新增用到的label
     * @return 字典数据集合信息
     */
    void checkDictIsExists(String dictType, String key, String defaultTypeLabel, String label);
=======
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
}
