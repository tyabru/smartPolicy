package com.jingyu.contradiction.service;

import java.util.List;
import com.jingyu.contradiction.domain.DisputingPersonnel;

/**
 * 参与人员信息Service接口
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
public interface IDisputingPersonnelService 
{
    /**
     * 查询参与人员信息
     * 
     * @param id 参与人员信息主键
     * @return 参与人员信息
     */
    public DisputingPersonnel selectDisputingPersonnelById(Long id);

    /**
     * 查询参与人员信息列表
     * 
     * @param disputingPersonnel 参与人员信息
     * @return 参与人员信息集合
     */
    public List<DisputingPersonnel> selectDisputingPersonnelList(DisputingPersonnel disputingPersonnel);

    /**
     * 新增参与人员信息
     * 
     * @param disputingPersonnel 参与人员信息
     * @return 结果
     */
    public int insertDisputingPersonnel(DisputingPersonnel disputingPersonnel);

    /**
     * 修改参与人员信息
     * 
     * @param disputingPersonnel 参与人员信息
     * @return 结果
     */
    public int updateDisputingPersonnel(DisputingPersonnel disputingPersonnel);

    /**
     * 批量删除参与人员信息
     * 
     * @param ids 需要删除的参与人员信息主键集合
     * @return 结果
     */
    public int deleteDisputingPersonnelByIds(Long[] ids);

    /**
     * 删除参与人员信息信息
     * 
     * @param id 参与人员信息主键
     * @return 结果
     */
    public int deleteDisputingPersonnelById(Long id);

    /**
     * 查询参与人员信息
     *
     * @param caseCode 案件编码
     * @return 参与人员信息
     */
    public List<DisputingPersonnel> getContradictionPersonByCaseCode(String caseCode);
}
