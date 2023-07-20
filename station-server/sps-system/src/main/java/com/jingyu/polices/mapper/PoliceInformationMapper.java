package com.jingyu.polices.mapper;

import java.util.List;

import com.jingyu.polices.domain.PoliceInformation;

/**
 * 警员基本信息Mapper接口
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
public interface PoliceInformationMapper
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
     * 删除警员基本信息
     * 
     * @param id 警员基本信息主键
     * @return 结果
     */
    public int deletePoliceInformationById(Long id);

    /**
     * 批量删除警员基本信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePoliceInformationByIds(Long[] ids);

    /**
     * 查询警员基本信息列表
     *
     * @param policeNumber 警号
     * @return 警员基本信息
     */
    public PoliceInformation selectPoliceInformationByPoliceNumber(String policeNumber);

    /**
     * 查询警员基本信息列表
     *
     * @param idCard 身份证号码
     * @return 警员基本信息
     */
    public PoliceInformation selectPoliceInformationByIdCard(String idCard);

    /**
     * 警员信息添加用户ID
     *
     * @param policeInformation 警员信息
     * @return 警员基本信息
     */
    public int updatePoliceInformationByPoliceNumber(PoliceInformation policeInformation);

    /**
     * 查询警员基本信息列表
     *
     * @param userId 用户ID
     * @return 警员基本信息
     */
    public PoliceInformation selectPoliceInformationByuserId(Long userId);
}
