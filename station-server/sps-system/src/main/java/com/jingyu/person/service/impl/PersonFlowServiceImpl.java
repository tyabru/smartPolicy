package com.jingyu.person.service.impl;

import java.util.*;

import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.file.FileUtils;
import com.jingyu.person.domain.PersonHouse;
import com.jingyu.person.domain.PersonResident;
import com.jingyu.person.service.IPersonHouseService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.person.mapper.PersonFlowMapper;
import com.jingyu.person.domain.PersonFlow;
import com.jingyu.person.service.IPersonFlowService;
import org.springframework.transaction.annotation.Transactional;

import static com.jingyu.person.PersonConstants.PERSON_TYPE_FLOW;

/**
 * 流动人口Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-21
 */
@Service
@RequiredArgsConstructor
public class PersonFlowServiceImpl implements IPersonFlowService 
{
    private final PersonFlowMapper personFlowMapper;
    private final IPersonHouseService personHouseService;

    /**
     * 查询流动人口
     * 
     * @param id 流动人口主键
     * @return 流动人口
     */
    @Override
    public PersonFlow selectPersonFlowById(Long id)
    {
        return personFlowMapper.selectPersonFlowById(id);
    }

    /**
     * 查询流动人口列表
     * 
     * @param personFlow 流动人口
     * @return 流动人口
     */
    @Override
    public List<PersonFlow> selectPersonFlowList(PersonFlow personFlow)
    {
        return personFlowMapper.selectPersonFlowList(personFlow);
    }

    /**
     * 新增流动人口
     * 
     * @param personFlow 流动人口
     * @return 结果
     */
    @Override
    @Transactional
    public int insertPersonFlow(PersonFlow personFlow)
    {
        Date now = DateUtils.getNowDate();
        personFlow.setCreateTime(now);
        personFlow.setCreateBy(SecurityUtils.getUsername());
        String date = DateFormatUtils.format(now, "yyyyMMddHHmmss");
        String isImportant = "Y".equals(personFlow.getIsImportant())? "01": "00";
        String bm = "6105" + isImportant + date;
        personFlow.setBm(bm);
        int i = personFlowMapper.insertPersonFlow(personFlow);
        if(i > 0) {
            handleHouseList(personFlow);
        }
        return i;
    }

    /**
     * 修改流动人口
     * 
     * @param personFlow 流动人口
     * @return 结果
     */
    @Override
    public int updatePersonFlow(PersonFlow personFlow)
    {
        personFlow.setUpdateTime(DateUtils.getNowDate());
        personFlow.setUpdateBy(SecurityUtils.getUsername());
        PersonFlow old = selectPersonFlowById(personFlow.getId());
        boolean needDelOldFile = !Objects.equals(personFlow.getFaceImgUrl(), old.getFaceImgUrl());
        int i = personFlowMapper.updatePersonFlow(personFlow);
        if(i > 0) {
            if(needDelOldFile) {
                FileUtils.deleteFile(old.getFaceImgUrl());
            }
            handleHouseList(personFlow);
        }
        return i;
    }

    private void handleHouseList(PersonFlow flow) {
        personHouseService.
                insertOrUpdateByPersonId(flow.getId(), PERSON_TYPE_FLOW, flow.getIsImportant(), flow.getHouseList());

    }

    /**
     * 批量删除流动人口
     * 
     * @param ids 需要删除的流动人口主键
     * @return 结果
     */
    @Override
    public int deletePersonFlowByIds(Long[] ids)
    {
        int i = 0;
        for (Long id : ids) {
            i += deletePersonFlowById(id);
        }
        return i;
    }

    /**
     * 删除流动人口信息
     * 
     * @param id 流动人口主键
     * @return 结果
     */
    @Override
    public int deletePersonFlowById(Long id)
    {
        PersonFlow resident = selectPersonFlowById(id);
        if(StringUtils.isNotEmpty(resident.getFaceImgUrl())) {
            FileUtils.deleteFileByProfileUrl(resident.getFaceImgUrl());
        }
        personHouseService.deleteByPersonId(id, PERSON_TYPE_FLOW);
        return personFlowMapper.deletePersonFlowById(id);
    }
}
