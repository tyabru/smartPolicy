package com.jingyu.person.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jingyu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.person.mapper.PersonFcousMapper;
import com.jingyu.person.domain.PersonFcous;
import com.jingyu.person.service.IPersonFcousService;

/**
 * 重点关注人口Service业务层处理
 * 
 * @author jingyu
 * @date 2023-08-01
 */
@Service
public class PersonFcousServiceImpl implements IPersonFcousService 
{
    @Autowired
    private PersonFcousMapper personFcousMapper;

    /**
     * 查询重点关注人口
     * 
     * @param id 重点关注人口主键
     * @return 重点关注人口
     */
    @Override
    public PersonFcous selectPersonFcousById(Long id)
    {
        return personFcousMapper.selectPersonFcousById(id);
    }

    /**
     * 查询重点关注人口列表
     * 
     * @param personFcous 重点关注人口
     * @return 重点关注人口
     */
    @Override
    public List<PersonFcous> selectPersonFcousList(PersonFcous personFcous)
    {
        return personFcousMapper.selectPersonFcousList(personFcous);
    }

    /**
     * 新增重点关注人口
     * 
     * @param personFcous 重点关注人口
     * @return 结果
     */
    @Override
    public int insertPersonFcous(PersonFcous personFcous)
    {
        personFcous.setCreateTime(DateUtils.getNowDate());
        return personFcousMapper.insertPersonFcous(personFcous);
    }

    /**
     * 修改重点关注人口
     * 
     * @param personFcous 重点关注人口
     * @return 结果
     */
    @Override
    public int updatePersonFcous(PersonFcous personFcous)
    {
        personFcous.setUpdateTime(DateUtils.getNowDate());
        return personFcousMapper.updatePersonFcous(personFcous);
    }

    /**
     * 批量删除重点关注人口
     * 
     * @param ids 需要删除的重点关注人口主键
     * @return 结果
     */
    @Override
    public int deletePersonFcousByIds(Long[] ids)
    {
        return personFcousMapper.deletePersonFcousByIds(ids);
    }

    /**
     * 删除重点关注人口信息
     * 
     * @param id 重点关注人口主键
     * @return 结果
     */
    @Override
    public int deletePersonFcousById(Long id)
    {
        return personFcousMapper.deletePersonFcousById(id);
    }

}
