package com.jingyu.person.service;

import java.util.List;
import com.jingyu.person.domain.PersonResident;

/**
 * 社区常驻人口Service接口
 * 
 * @author jingyu
 * @date 2023-07-10
 */
public interface IPersonResidentService 
{
    /**
     * 查询社区常驻人口
     * 
     * @param id 社区常驻人口主键
     * @return 社区常驻人口
     */
    public PersonResident selectPersonResidentById(Long id);

    /**
     * 查询社区常驻人口列表
     * 
     * @param personResident 社区常驻人口
     * @return 社区常驻人口集合
     */
    public List<PersonResident> selectPersonResidentList(PersonResident personResident);

    /**
     * 新增社区常驻人口
     * 
     * @param personResident 社区常驻人口
     * @return 结果
     */
    public int insertPersonResident(PersonResident personResident);

    /**
     * 修改社区常驻人口
     * 
     * @param personResident 社区常驻人口
     * @return 结果
     */
    public int updatePersonResident(PersonResident personResident);

    /**
     * 批量删除社区常驻人口
     * 
     * @param ids 需要删除的社区常驻人口主键集合
     * @return 结果
     */
    public int deletePersonResidentByIds(Long[] ids);

    /**
     * 删除社区常驻人口信息
     * 
     * @param id 社区常驻人口主键
     * @return 结果
     */
    public int deletePersonResidentById(Long id);
}
