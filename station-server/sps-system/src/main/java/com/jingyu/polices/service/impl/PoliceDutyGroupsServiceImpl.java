package com.jingyu.polices.service.impl;

import java.util.List;

import com.jingyu.common.utils.StringUtils;
import com.jingyu.polices.domain.PoliceInformation;
import com.jingyu.polices.mapper.PoliceInformationMapper;
import org.springframework.stereotype.Service;
import com.jingyu.polices.mapper.PoliceDutyGroupsMapper;
import com.jingyu.polices.domain.PoliceDutyGroups;
import com.jingyu.polices.service.IPoliceDutyGroupsService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 警员分组详情Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-07-04
 */
@Service
public class PoliceDutyGroupsServiceImpl implements IPoliceDutyGroupsService 
{
    @Resource
    private PoliceDutyGroupsMapper policeDutyGroupsMapper;

    @Resource
    private PoliceInformationMapper policeInformationMapper;

    /**
     * 查询警员分组详情
     * 
     * @param id 警员分组详情主键
     * @return 警员分组详情
     */
    @Override
    public PoliceDutyGroups selectPoliceDutyGroupsById(Long id)
    {
        PoliceDutyGroups policeDutyGroups = policeDutyGroupsMapper.selectPoliceDutyGroupsById(id);
        policeNumberToPoliceName(policeDutyGroups);
        return policeDutyGroups;
    }

    /**
     * 查询警员分组详情列表
     * 
     * @param policeDutyGroups 警员分组详情
     * @return 警员分组详情
     */
    @Override
    public List<PoliceDutyGroups> selectPoliceDutyGroupsList(PoliceDutyGroups policeDutyGroups)
    {
        List<PoliceDutyGroups> list = policeDutyGroupsMapper.selectPoliceDutyGroupsList(policeDutyGroups);
        for (PoliceDutyGroups dutyGroups : list) {
            policeNumberToPoliceName(dutyGroups);
        }
        return list;
    }

    //警号查询警员姓名
    public PoliceDutyGroups policeNumberToPoliceName(PoliceDutyGroups policeDutyGroups) {
        String policeNames = "";
        String policeNumbers = "";
        String[] policeList = policeDutyGroups.getTeamMembers().split(",");
        for (String policeNumber : policeList) {
            PoliceInformation policeInformation = policeInformationMapper.selectPoliceInformationByPoliceNumber(policeNumber);
            if (StringUtils.isNotNull(policeInformation)) {
                policeNames += policeInformation.getPoliceName() + ",";
                policeNumbers += policeNumber + ",";
            }
        }
        if (policeNames.length() > 0) {
            policeNames = policeNames.substring(0, policeNames.length() - 1);
            policeDutyGroups.setPoliceNames(policeNames);
        }
        if (policeNumbers.length() > 0) {
            policeNumbers = policeNumbers.substring(0, policeNumbers.length() - 1);
            policeDutyGroups.setTeamMembers(policeNumbers);
        }
        return policeDutyGroups;
    }

    /**
     * 新增警员分组详情
     * 
     * @param policeDutyGroups 警员分组详情
     * @return 结果
     */
    @Override
    @Transactional
    public int insertPoliceDutyGroups(PoliceDutyGroups policeDutyGroups)
    {
        return policeDutyGroupsMapper.insertPoliceDutyGroups(policeDutyGroups);
    }

    /**
     * 修改警员分组详情
     * 
     * @param policeDutyGroups 警员分组详情
     * @return 结果
     */
    @Override
    @Transactional
    public int updatePoliceDutyGroups(PoliceDutyGroups policeDutyGroups)
    {
        return policeDutyGroupsMapper.updatePoliceDutyGroups(policeDutyGroups);
    }

    /**
     * 批量删除警员分组详情
     * 
     * @param ids 需要删除的警员分组详情主键
     * @return 结果
     */
    @Override
    public int deletePoliceDutyGroupsByIds(Long[] ids)
    {
        return policeDutyGroupsMapper.deletePoliceDutyGroupsByIds(ids);
    }

    /**
     * 删除警员分组详情信息
     * 
     * @param id 警员分组详情主键
     * @return 结果
     */
    @Override
    public int deletePoliceDutyGroupsById(Long id)
    {
        return policeDutyGroupsMapper.deletePoliceDutyGroupsById(id);
    }
}
