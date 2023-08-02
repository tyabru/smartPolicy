package com.jingyu.person.service;

import java.util.List;
import com.jingyu.person.domain.PersonFlow;

/**
 * 流动人口Service接口
 * 
 * @author jingyu
 * @date 2023-07-21
 */
public interface IPersonFlowService 
{
    /**
     * 查询流动人口
     * 
     * @param id 流动人口主键
     * @return 流动人口
     */
    public PersonFlow selectPersonFlowById(Long id);

    /**
     * 查询流动人口列表
     * 
     * @param personFlow 流动人口
     * @return 流动人口集合
     */
    public List<PersonFlow> selectPersonFlowList(PersonFlow personFlow);

    /**
     * 新增流动人口
     * 
     * @param personFlow 流动人口
     * @return 结果
     */
    public int insertPersonFlow(PersonFlow personFlow);

    /**
     * 修改流动人口
     * 
     * @param personFlow 流动人口
     * @return 结果
     */
    public int updatePersonFlow(PersonFlow personFlow);

    /**
     * 批量删除流动人口
     * 
     * @param ids 需要删除的流动人口主键集合
     * @return 结果
     */
    public int deletePersonFlowByIds(Long[] ids);

    /**
     * 删除流动人口信息
     * 
     * @param id 流动人口主键
     * @return 结果
     */
    public int deletePersonFlowById(Long id);
}
