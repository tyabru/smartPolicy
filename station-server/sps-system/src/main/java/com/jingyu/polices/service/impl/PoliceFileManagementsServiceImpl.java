package com.jingyu.polices.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.polices.mapper.PoliceFileManagementsMapper;
import com.jingyu.polices.domain.PoliceFileManagements;
import com.jingyu.polices.service.IPoliceFileManagementsService;

/**
 * 日常文件管理Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Service
public class PoliceFileManagementsServiceImpl implements IPoliceFileManagementsService 
{
    @Autowired
    private PoliceFileManagementsMapper policeFileManagementsMapper;

    /**
     * 查询日常文件管理
     * 
     * @param id 日常文件管理主键
     * @return 日常文件管理
     */
    @Override
    public PoliceFileManagements selectPoliceFileManagementsById(Long id)
    {
        return policeFileManagementsMapper.selectPoliceFileManagementsById(id);
    }

    /**
     * 查询日常文件管理列表
     * 
     * @param policeFileManagements 日常文件管理
     * @return 日常文件管理
     */
    @Override
    public List<PoliceFileManagements> selectPoliceFileManagementsList(PoliceFileManagements policeFileManagements)
    {
        return policeFileManagementsMapper.selectPoliceFileManagementsList(policeFileManagements);
    }

    /**
     * 新增日常文件管理
     * 
     * @param policeFileManagements 日常文件管理
     * @return 结果
     */
    @Override
    public int insertPoliceFileManagements(PoliceFileManagements policeFileManagements)
    {
        return policeFileManagementsMapper.insertPoliceFileManagements(policeFileManagements);
    }

    /**
     * 修改日常文件管理
     * 
     * @param policeFileManagements 日常文件管理
     * @return 结果
     */
    @Override
    public int updatePoliceFileManagements(PoliceFileManagements policeFileManagements)
    {
        return policeFileManagementsMapper.updatePoliceFileManagements(policeFileManagements);
    }

    /**
     * 批量删除日常文件管理
     * 
     * @param ids 需要删除的日常文件管理主键
     * @return 结果
     */
    @Override
    public int deletePoliceFileManagementsByIds(Long[] ids)
    {
        return policeFileManagementsMapper.deletePoliceFileManagementsByIds(ids);
    }

    /**
     * 删除日常文件管理信息
     * 
     * @param id 日常文件管理主键
     * @return 结果
     */
    @Override
    public int deletePoliceFileManagementsById(Long id)
    {
        return policeFileManagementsMapper.deletePoliceFileManagementsById(id);
    }
}
