package com.jingyu.contradiction.service;

import java.util.List;
import com.jingyu.contradiction.domain.ContradictionTangles;

/**
 * 矛盾纠纷基本信息Service接口
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
public interface IContradictionTanglesService 
{
    /**
     * 查询矛盾纠纷基本信息
     * 
     * @param id 矛盾纠纷基本信息主键
     * @return 矛盾纠纷基本信息
     */
    public ContradictionTangles selectContradictionTanglesById(Long id);

    /**
     * 查询矛盾纠纷基本信息列表
     * 
     * @param contradictionTangles 矛盾纠纷基本信息
     * @return 矛盾纠纷基本信息集合
     */
    public List<ContradictionTangles> selectContradictionTanglesList(ContradictionTangles contradictionTangles);

    /**
     * 新增矛盾纠纷基本信息
     * 
     * @param contradictionTangles 矛盾纠纷基本信息
     * @return 结果
     */
    public int insertContradictionTangles(ContradictionTangles contradictionTangles);

    /**
     * 修改矛盾纠纷基本信息
     * 
     * @param contradictionTangles 矛盾纠纷基本信息
     * @return 结果
     */
    public int updateContradictionTangles(ContradictionTangles contradictionTangles);

    /**
     * 批量删除矛盾纠纷基本信息
     * 
     * @param ids 需要删除的矛盾纠纷基本信息主键集合
     * @return 结果
     */
    public int deleteContradictionTanglesByIds(Long[] ids);

    /**
     * 删除矛盾纠纷基本信息信息
     * 
     * @param id 矛盾纠纷基本信息主键
     * @return 结果
     */
    public int deleteContradictionTanglesById(Long id);

    /**
     * 查询矛盾纠纷基本信息
     *
     * @param caseCode 矛盾纠纷案件编码
     * @return 矛盾纠纷基本信息
     */
    public ContradictionTangles getContradictionByCaseCode(String caseCode);
}
