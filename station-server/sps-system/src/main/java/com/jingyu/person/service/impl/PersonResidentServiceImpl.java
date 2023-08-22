package com.jingyu.person.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.jingyu.common.exception.CustomException;
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
import com.jingyu.person.mapper.PersonResidentMapper;
import com.jingyu.person.domain.PersonResident;
import com.jingyu.person.service.IPersonResidentService;
import org.springframework.transaction.annotation.Transactional;

import static com.jingyu.person.PersonConstants.PERSON_IS_IMPORTANT;
import static com.jingyu.person.PersonConstants.PERSON_TYPE_RESIDENT;

/**
 * 社区常驻人口Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-10
 */
@Service
@RequiredArgsConstructor
public class PersonResidentServiceImpl implements IPersonResidentService 
{
    private final PersonResidentMapper personResidentMapper;

    private final IPersonHouseService personHouseService;

    private final IPersonFcousService personFcousService;

    /**
     * 查询社区常驻人口
     * 
     * @param id 社区常驻人口主键
     * @return 社区常驻人口
     */
    @Override
    public PersonResident selectPersonResidentById(Long id)
    {
        PersonResident personResident = personResidentMapper.selectPersonResidentById(id);
        if (personResident.getIsImportant().equals("Y")) {
            PersonFcous person = new PersonFcous();
            person.setPersonId(personResident.getId());
            person.setPersonType(1L);
            List<PersonFcous> personFcous = personFcousService.selectPersonFcousList(person);
            personResident.setFocusReason(personFcous.get(0).getFocusReason());
            personResident.setReasonDesc(personFcous.get(0).getReasonDesc());
        }
        return personResident;
    }

    /**
     * 查询社区常驻人口列表
     * 
     * @param personResident 社区常驻人口
     * @return 社区常驻人口
     */
    @Override
    public List<PersonResident> selectPersonResidentList(PersonResident personResident)
    {
        return personResidentMapper.selectPersonResidentList(personResident);
    }

    /**
     * 新增社区常驻人口
     * 
     * @param personResident 社区常驻人口
     * @return 结果
     */
    @Override
    @Transactional
    public int insertPersonResident(PersonResident personResident)
    {
        String certNo = personResident.getCertNo();
        PersonResident temp = new PersonResident();
        temp.setCertNo(certNo);
        List<PersonResident> tempList = selectPersonResidentList(temp);
        if(tempList != null && tempList.size() > 0) {
            throw new CustomException("此身份证号在常驻人员中已存在！");
        }
        Date now = DateUtils.getNowDate();
        personResident.setCreateTime(now);
        personResident.setCreateBy(SecurityUtils.getUsername());
        String date = DateFormatUtils.format(now, "yyyyMMddHHmmss");
        String isImportant = "Y".equals(personResident.getIsImportant())? "01": "00";
        String bm = "6105" + isImportant + date;
        personResident.setBm(bm);
        if(StringUtils.isEmpty(personResident.getSourcePlatform())) {
            personResident.setSourcePlatform("web");
        }
        int i = personResidentMapper.insertPersonResident(personResident);
        if(i > 0) {
            //添加位重点关注人员
            if (personResident.getIsImportant().equals(PERSON_IS_IMPORTANT)) {
                PersonResident person = personResidentMapper.selectPersonResidentByCerNo(personResident.getCertNo());
                personResident.setId(person.getId());
                PersonFcous personFcous = addPersonFcous(personResident);
                List<PersonHouse> houseList = personResident.getHouseList();
                for (PersonHouse personHouse : houseList) {
                    addPersonFcousHouse(personFcous,personHouse);
                    personFcousService.insertPersonFcous(personFcous);
                }
            }
            handleHouseList(personResident);
        }
        return i;
    }

    /**
     * 添加为重点关注人员
     * */
    public PersonFcous addPersonFcous(PersonResident personResident) {
        PersonFcous personFcous = new PersonFcous();
        personFcous.setPersonId(personResident.getId());
        personFcous.setPersonType(PERSON_TYPE_RESIDENT);
        personFcous.setFocusReason(personResident.getFocusReason());
        personFcous.setLevel(personResident.getAttentionLevel());
        personFcous.setReasonDesc(personResident.getReasonDesc());
        personFcous.setNativePalce(personResident.getNativePlace());
        personFcous.setName(personResident.getName());
        personFcous.setCertType(personResident.getCertType());
        personFcous.setCertNo(personResident.getCertNo());
        personFcous.setPhone(personResident.getPhone());
        personFcous.setFaceImgUrl(personResident.getFaceImgUrl());
        personFcous.setFeatures(personResident.getFeatures());
        personFcous.setCreateBy(personResident.getCreateBy());
        personFcous.setSourcePlatform(personResident.getSourcePlatform());
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
     * 修改社区常驻人口
     * 
     * @param personResident 社区常驻人口
     * @return 结果
     */
    @Override
    @Transactional
    public int updatePersonResident(PersonResident personResident)
    {
        personResident.setUpdateTime(DateUtils.getNowDate());
        personResident.setUpdateBy(SecurityUtils.getUsername());
        PersonResident old = selectPersonResidentById(personResident.getId());
        old.setCertNo(AESUtil.decrypt(old.getCertNo()));
        old.setPhone(AESUtil.decrypt(old.getPhone()));
        SensitiveNewsHander.revertNotEditAttrs(personResident,old);
        SensitiveNewsHander.revertEncryptAttrs(personResident);
        boolean needDelOldFile = !Objects.equals(personResident.getFaceImgUrl(), old.getFaceImgUrl());
        int i = personResidentMapper.updatePersonResident(personResident);
        if(i > 0) {
            if(needDelOldFile) {
                FileUtils.deleteFile(old.getFaceImgUrl());
            }
            if (personResident.getIsImportant().equals(PERSON_IS_IMPORTANT)) {
                PersonFcous personFcous = addPersonFcous(personResident);
                List<PersonHouse> houseList = personResident.getHouseList();
                for (PersonHouse personHouse : houseList) {
                    addPersonFcousHouse(personFcous,personHouse);
                    personFcousService.updatePersonFcous(personFcous);
                }
            }
            handleHouseList(personResident);
        }
        return i;
    }

    private void handleHouseList(PersonResident resident) {
        personHouseService.
                insertOrUpdateByPersonId(resident.getId(), PERSON_TYPE_RESIDENT, resident.getIsImportant(), resident.getHouseList());

    }

    /**
     * 批量删除社区常驻人口
     * 
     * @param ids 需要删除的社区常驻人口主键
     * @return 结果
     */
    @Override
    public int deletePersonResidentByIds(Long[] ids)
    {
        int i = 0;
        for (Long id : ids) {
            i += deletePersonResidentById(id);
        }
        return i;
    }

    /**
     * 删除社区常驻人口信息
     * 
     * @param id 社区常驻人口主键
     * @return 结果
     */
    @Override
    public int deletePersonResidentById(Long id)
    {
        PersonResident resident = selectPersonResidentById(id);
        if (resident.getIsImportant().equals(PERSON_IS_IMPORTANT)) {
            personFcousService.deletePersonFcousByPersonId(id,PERSON_TYPE_RESIDENT);
        }
        if(StringUtils.isNotEmpty(resident.getFaceImgUrl())) {
            FileUtils.deleteFileByProfileUrl(resident.getFaceImgUrl());
        }
        personHouseService.deleteByPersonId(id, PERSON_TYPE_RESIDENT);
        return personResidentMapper.deletePersonResidentById(id);
    }
}
