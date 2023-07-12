package com.jingyu.person.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.person.mapper.PersonResidentMapper;
import com.jingyu.person.domain.PersonResident;
import com.jingyu.person.service.IPersonResidentService;

/**
 * 社区常驻人口Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-10
 */
@Service
public class PersonResidentServiceImpl implements IPersonResidentService 
{
    @Autowired
    private PersonResidentMapper personResidentMapper;

    /**
     * 查询社区常驻人口
     * 
     * @param id 社区常驻人口主键
     * @return 社区常驻人口
     */
    @Override
    public PersonResident selectPersonResidentById(Long id)
    {
        return personResidentMapper.selectPersonResidentById(id);
    }

    /**
     * 查询社区常驻人口列表
     * 
     * @param personResident 社区常驻人口
     * @return 社区常驻人口
     */
    @Override
    public List<PersonResident> selectPersonResidentList(PersonResident personResident)
    {
        return personResidentMapper.selectPersonResidentList(personResident);
    }

    /**
     * 新增社区常驻人口
     * 
     * @param personResident 社区常驻人口
     * @return 结果
     */
    @Override
    public int insertPersonResident(PersonResident personResident)
    {
        personResident.setCreateTime(DateUtils.getNowDate());
        return personResidentMapper.insertPersonResident(personResident);
    }

    /**
     * 修改社区常驻人口
     * 
     * @param personResident 社区常驻人口
     * @return 结果
     */
    @Override
    public int updatePersonResident(PersonResident personResident)
    {
        personResident.setUpdateTime(DateUtils.getNowDate());
        return personResidentMapper.updatePersonResident(personResident);
    }

    /**
     * 批量删除社区常驻人口
     * 
     * @param ids 需要删除的社区常驻人口主键
     * @return 结果
     */
    @Override
    public int deletePersonResidentByIds(Long[] ids)
    {
        return personResidentMapper.deletePersonResidentByIds(ids);
    }

    /**
     * 删除社区常驻人口信息
     * 
     * @param id 社区常驻人口主键
     * @return 结果
     */
    @Override
    public int deletePersonResidentById(Long id)
    {
        return personResidentMapper.deletePersonResidentById(id);
    }
}
