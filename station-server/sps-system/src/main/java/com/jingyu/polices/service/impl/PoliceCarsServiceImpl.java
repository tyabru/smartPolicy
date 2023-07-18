package com.jingyu.polices.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.polices.mapper.PoliceCarsMapper;
import com.jingyu.polices.domain.PoliceCars;
import com.jingyu.polices.service.IPoliceCarsService;

/**
 * 警用车辆Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Service
public class PoliceCarsServiceImpl implements IPoliceCarsService 
{
    @Autowired
    private PoliceCarsMapper policeCarsMapper;

    /**
     * 查询警用车辆
     * 
     * @param id 警用车辆主键
     * @return 警用车辆
     */
    @Override
    public PoliceCars selectPoliceCarsById(Long id)
    {
        return policeCarsMapper.selectPoliceCarsById(id);
    }

    /**
     * 查询警用车辆列表
     * 
     * @param policeCars 警用车辆
     * @return 警用车辆
     */
    @Override
    public List<PoliceCars> selectPoliceCarsList(PoliceCars policeCars)
    {
        return policeCarsMapper.selectPoliceCarsList(policeCars);
    }

    /**
     * 新增警用车辆
     * 
     * @param policeCars 警用车辆
     * @return 结果
     */
    @Override
    public int insertPoliceCars(PoliceCars policeCars)
    {
        return policeCarsMapper.insertPoliceCars(policeCars);
    }

    /**
     * 修改警用车辆
     * 
     * @param policeCars 警用车辆
     * @return 结果
     */
    @Override
    public int updatePoliceCars(PoliceCars policeCars)
    {
        return policeCarsMapper.updatePoliceCars(policeCars);
    }

    /**
     * 批量删除警用车辆
     * 
     * @param ids 需要删除的警用车辆主键
     * @return 结果
     */
    @Override
    public int deletePoliceCarsByIds(Long[] ids)
    {
        return policeCarsMapper.deletePoliceCarsByIds(ids);
    }

    /**
     * 删除警用车辆信息
     * 
     * @param id 警用车辆主键
     * @return 结果
     */
    @Override
    public int deletePoliceCarsById(Long id)
    {
        return policeCarsMapper.deletePoliceCarsById(id);
    }
}
