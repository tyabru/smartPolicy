package com.jingyu.polices.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.polices.domain.PoliceDutyGroups;
import com.jingyu.polices.domain.PoliceInformation;
import com.jingyu.polices.mapper.PoliceDutyGroupsMapper;
import com.jingyu.polices.mapper.PoliceInformationMapper;
import com.jingyu.polices.service.IPoliceDutyGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.polices.mapper.PoliceWorkforceManagementsMapper;
import com.jingyu.polices.domain.PoliceWorkforceManagements;
import com.jingyu.polices.service.IPoliceWorkforceManagementsService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 排班管理Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Service
public class PoliceWorkforceManagementsServiceImpl implements IPoliceWorkforceManagementsService 
{
    @Resource
    private PoliceWorkforceManagementsMapper policeWorkforceManagementsMapper;

    @Resource
    private IPoliceDutyGroupsService policeDutyGroupsService;

    @Resource
    private PoliceInformationMapper policeInformationMapper;

    /**
     * 查询排班管理
     * 
     * @param id 排班管理主键
     * @return 排班管理
     */
    @Override
    public PoliceWorkforceManagements selectPoliceWorkforceManagementsById(Long id)
    {
        return policeWorkforceManagementsMapper.selectPoliceWorkforceManagementsById(id);
    }

    /**
     * 查询排班管理
     *
     * @param date 排班日期
     * @return 排班管理
     */
    @Override
    public PoliceWorkforceManagements selectPoliceWorkforceManagementsByDutyDate(String date) {
        PoliceWorkforceManagements policeWorkforceManagements =
                policeWorkforceManagementsMapper.selectPoliceWorkforceManagementsByDutyDate(date);
        if (policeWorkforceManagements != null) {
            policeNumberToPoliceName(policeWorkforceManagements);
        } else {
            PoliceWorkforceManagements policeManagements = new PoliceWorkforceManagements();
            policeManagements.setPoliceNames("未排班");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                policeManagements.setDutyDate(simpleDateFormat.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            policeWorkforceManagements = policeManagements;
        }
        return policeWorkforceManagements;
    }

    /**
     * 查询排班管理列表
     * 
     * @param policeWorkforceManagements 排班管理
     * @return 排班管理
     */
    @Override
    public List<PoliceWorkforceManagements> selectPoliceWorkforceManagementsList(PoliceWorkforceManagements policeWorkforceManagements)
    {
        List<PoliceWorkforceManagements> managementsList =
                policeWorkforceManagementsMapper.selectPoliceWorkforceManagementsList(policeWorkforceManagements);
        for (PoliceWorkforceManagements workforceManagements : managementsList) {
            policeNumberToPoliceName(workforceManagements);
        }
        return managementsList;
    }

    //警号查询警员姓名
    public PoliceWorkforceManagements policeNumberToPoliceName(PoliceWorkforceManagements policeWorkforceManagements) {
        String policeNames = "";
        String[] policeList = policeWorkforceManagements.getPoliceNumber().split(",");
        for (String policeNumber : policeList) {
            PoliceInformation policeInformation = policeInformationMapper.selectPoliceInformationByPoliceNumber(policeNumber);
            policeNames += policeInformation.getPoliceName() + ",";
        }
        if (policeNames.length() > 0) {
            policeNames = policeNames.substring(0, policeNames.length() - 1);
            policeWorkforceManagements.setPoliceNames(policeNames);
        }
        return policeWorkforceManagements;
    }

    /**
     * 新增排班管理
     * 
     * @param policeWorkforceManagements 排班管理
     * @return 结果
     */
    @Override
    @Transactional
    public int insertPoliceWorkforceManagements(PoliceWorkforceManagements policeWorkforceManagements)
    {
        PoliceDutyGroups dutyGroups = policeDutyGroupsService.selectPoliceDutyGroupsById(
                Long.parseLong(policeWorkforceManagements.getDutyGroupId().toString()));
        if (StringUtils.isNotNull(dutyGroups)) {
            policeWorkforceManagements.setPoliceNumber(dutyGroups.getTeamMembers());
        }
        PoliceDutyGroups SuccessorGroups = policeDutyGroupsService.selectPoliceDutyGroupsById(
                Long.parseLong(policeWorkforceManagements.getSuccessorGroupId().toString()));
        if (StringUtils.isNotNull(dutyGroups)) {
            policeWorkforceManagements.setSuccessorPoliceNumber(SuccessorGroups.getTeamMembers());
        }
        return policeWorkforceManagementsMapper.insertPoliceWorkforceManagements(policeWorkforceManagements);
    }

    /**
     * 修改排班管理
     * 
     * @param policeWorkforceManagements 排班管理
     * @return 结果
     */
    @Override
    @Transactional
    public int updatePoliceWorkforceManagements(PoliceWorkforceManagements policeWorkforceManagements)
    {
        return policeWorkforceManagementsMapper.updatePoliceWorkforceManagements(policeWorkforceManagements);
    }

    /**
     * 批量删除排班管理
     * 
     * @param ids 需要删除的排班管理主键
     * @return 结果
     */
    @Override
    public int deletePoliceWorkforceManagementsByIds(Long[] ids)
    {
        return policeWorkforceManagementsMapper.deletePoliceWorkforceManagementsByIds(ids);
    }

    /**
     * 删除排班管理信息
     * 
     * @param id 排班管理主键
     * @return 结果
     */
    @Override
    public int deletePoliceWorkforceManagementsById(Long id)
    {
        return policeWorkforceManagementsMapper.deletePoliceWorkforceManagementsById(id);
    }
}
