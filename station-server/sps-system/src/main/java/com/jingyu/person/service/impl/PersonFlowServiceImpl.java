package com.jingyu.person.service.impl;

import java.util.*;

import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.encryption_decryption.SensitiveNewsHander;
import com.jingyu.common.utils.file.FileUtils;
import com.jingyu.common.utils.sign.AESUtil;
import com.jingyu.person.domain.PersonFcous;
import com.jingyu.person.domain.PersonHouse;
import com.jingyu.person.service.IPersonFcousService;
import com.jingyu.person.service.IPersonHouseService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import com.jingyu.person.mapper.PersonFlowMapper;
import com.jingyu.person.domain.PersonFlow;
import com.jingyu.person.service.IPersonFlowService;
import org.springframework.transaction.annotation.Transactional;

import static com.jingyu.person.PersonConstants.PERSON_IS_IMPORTANT;
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

    private final IPersonFcousService personFcousService;

    /**
     * 查询流动人口
     * 
     * @param id 流动人口主键
     * @return 流动人口
     */
    @Override
    public PersonFlow selectPersonFlowById(Long id)
    {
        PersonFlow personFlow = personFlowMapper.selectPersonFlowById(id);
        if (personFlow.getIsImportant().equals("Y")) {
            PersonFcous person = new PersonFcous();
            person.setPersonId(personFlow.getId());
            person.setPersonType(PERSON_TYPE_FLOW);
            List<PersonFcous> personFcous = personFcousService.selectPersonFcousList(person);
            personFlow.setFocusReason(personFcous.get(0).getFocusReason());
            personFlow.setReasonDesc(personFcous.get(0).getReasonDesc());
        }
        return personFlow;
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
            //添加位重点关注人员
            if (personFlow.getIsImportant().equals(PERSON_IS_IMPORTANT)) {
                PersonFlow person = personFlowMapper.selectPersonFlowByCerNo(personFlow.getCertNo());
                personFlow.setId(person.getId());
                PersonFcous personFcous = addPersonFcous(personFlow);
                List<PersonHouse> houseList = personFlow.getHouseList();
                for (PersonHouse personHouse : houseList) {
                    addPersonFcousHouse(personFcous,personHouse);
                    personFcousService.insertPersonFcous(personFcous);
                }
            }
            handleHouseList(personFlow);
        }
        return i;
    }

    /**
     * 添加为重点关注人员
     * */
    public PersonFcous addPersonFcous(PersonFlow personFlow) {
        PersonFcous personFcous = new PersonFcous();
        personFcous.setPersonId(personFlow.getId());
        personFcous.setPersonType(2L);
        personFcous.setFocusReason(personFlow.getFocusReason());
        personFcous.setLevel(personFlow.getAttentionLevel());
        personFcous.setReasonDesc(personFlow.getReasonDesc());
        personFcous.setNativePalce(personFlow.getNativePlace());
        personFcous.setName(personFlow.getName());
        personFcous.setCertType(personFlow.getCertType());
        personFcous.setCertNo(personFlow.getCertNo());
        personFcous.setPhone(personFlow.getPhone());
        personFcous.setFaceImgUrl(personFlow.getFaceImgUrl());
        personFcous.setFeatures(personFlow.getFeatures());
        personFcous.setCreateBy(personFlow.getCreateBy());
        personFcous.setSourcePlatform(personFlow.getSourcePlatform());
        return personFcous;
    }

    /**
     * 添加居住房屋信息
     * */
    public PersonFcous addPersonFcousHouse(PersonFcous personFcous,PersonHouse personHouse) {
        personFcous.setXqId(personHouse.getCommunityId());
        personFcous.setAddress(personHouse.getFullAddress());
        personFcous.setMetaAddrId(personHouse.getMetaAddrId());
        personFcous.setLivingDate(personHouse.getStartTime());
        return personFcous;
    }

    /**
     * 修改流动人口
     * 
     * @param personFlow 流动人口
     * @return 结果
     */
    @Override
    @Transactional
    public int updatePersonFlow(PersonFlow personFlow)
    {
        personFlow.setUpdateTime(DateUtils.getNowDate());
        personFlow.setUpdateBy(SecurityUtils.getUsername());
        PersonFlow old = selectPersonFlowById(personFlow.getId());
        old.setCertNo(AESUtil.decrypt(old.getCertNo()));
        old.setPhone(AESUtil.decrypt(old.getPhone()));
        SensitiveNewsHander.revertNotEditAttrs(personFlow,old);
        SensitiveNewsHander.revertEncryptAttrs(personFlow);
        boolean needDelOldFile = !Objects.equals(personFlow.getFaceImgUrl(), old.getFaceImgUrl());
        int i = personFlowMapper.updatePersonFlow(personFlow);
        if(i > 0) {
            if(needDelOldFile) {
                FileUtils.deleteFile(old.getFaceImgUrl());
            }
            if (personFlow.getIsImportant().equals(PERSON_IS_IMPORTANT)) {
                PersonFcous personFcous = addPersonFcous(personFlow);
                List<PersonHouse> houseList = personFlow.getHouseList();
                for (PersonHouse personHouse : houseList) {
                    addPersonFcousHouse(personFcous,personHouse);
                    personFcousService.updatePersonFcous(personFcous);
                }
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
    @Transactional
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
    @Transactional
    public int deletePersonFlowById(Long id)
    {
        PersonFlow resident = selectPersonFlowById(id);
        if (resident.getIsImportant().equals(PERSON_IS_IMPORTANT)) {
            personFcousService.deletePersonFcousByPersonId(id,PERSON_TYPE_FLOW);
        }
        if(StringUtils.isNotEmpty(resident.getFaceImgUrl())) {
            FileUtils.deleteFileByProfileUrl(resident.getFaceImgUrl());
        }
        personHouseService.deleteByPersonId(id, PERSON_TYPE_FLOW);
        return personFlowMapper.deletePersonFlowById(id);
    }
}
