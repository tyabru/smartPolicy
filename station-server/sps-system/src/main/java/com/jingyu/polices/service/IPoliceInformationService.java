package com.jingyu.polices.service;

import java.util.List;

import com.jingyu.polices.domain.PoliceInformation;

/**
 * 警员基本信息Service接口
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
public interface IPoliceInformationService
{
    /**
     * 查询警员基本信息
     * 
     * @param id 警员基本信息主键
     * @return 警员基本信息
     */
    public PoliceInformation selectPoliceInformationById(Long id);

    /**
     * 查询警员基本信息列表
     * 
     * @param policeInformation 警员基本信息
     * @return 警员基本信息集合
     */
    public List<PoliceInformation> selectPoliceInformationList(PoliceInformation policeInformation);

    /**
     * 新增警员基本信息
     * 
     * @param policeInformation 警员基本信息
     * @return 结果
     */
    public int insertPoliceInformation(PoliceInformation policeInformation);

    /**
     * 修改警员基本信息
     * 
     * @param policeInformation 警员基本信息
     * @return 结果
     */
    public int updatePoliceInformation(PoliceInformation policeInformation);

    /**
     * 批量删除警员基本信息
     * 
     * @param ids 需要删除的警员基本信息主键集合
     * @return 结果
     */
    public int deletePoliceInformationByIds(Long[] ids);

    /**
     * 删除警员基本信息信息
     * 
     * @param id 警员基本信息主键
     * @return 结果
     */
    public int deletePoliceInformationById(Long id);

    /**
     * 查询警员基本信息列表
     *
     * @param idCard 身份证号码
     * @return 警员基本信息
     */
    public PoliceInformation selectPoliceInformationByIdCard(String idCard);
}
