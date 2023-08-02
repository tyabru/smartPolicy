package com.jingyu.person.mapper;

import java.util.List;
import com.jingyu.person.domain.PersonFcous;

/**
 * 重点关注人口Mapper接口
 * 
 * @author jingyu
 * @date 2023-08-01
 */
public interface PersonFcousMapper 
{
    /**
     * 查询重点关注人口
     * 
     * @param id 重点关注人口主键
     * @return 重点关注人口
     */
    public PersonFcous selectPersonFcousById(Long id);

    /**
     * 查询重点关注人口列表
     * 
     * @param personFcous 重点关注人口
     * @return 重点关注人口集合
     */
    public List<PersonFcous> selectPersonFcousList(PersonFcous personFcous);

    /**
     * 新增重点关注人口
     * 
     * @param personFcous 重点关注人口
     * @return 结果
     */
    public int insertPersonFcous(PersonFcous personFcous);

    /**
     * 修改重点关注人口
     * 
     * @param personFcous 重点关注人口
     * @return 结果
     */
    public int updatePersonFcous(PersonFcous personFcous);

    /**
     * 删除重点关注人口
     * 
     * @param id 重点关注人口主键
     * @return 结果
     */
    public int deletePersonFcousById(Long id);

    /**
     * 批量删除重点关注人口
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePersonFcousByIds(Long[] ids);
}
