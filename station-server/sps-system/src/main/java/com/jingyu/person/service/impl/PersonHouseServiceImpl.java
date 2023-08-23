package com.jingyu.person.service.impl;

import com.jingyu.community.domain.Community;
import com.jingyu.community.domain.CommunityDetail;
import com.jingyu.community.service.ICommunityDetailService;
import com.jingyu.person.domain.HouseAddrVo;
import com.jingyu.person.domain.PersonHouse;
import com.jingyu.person.mapper.PersonHouseMapper;
import com.jingyu.person.service.IPersonHouseService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.jingyu.person.PersonConstants.PERSON_TYPE_FLOW;
import static com.jingyu.person.PersonConstants.PERSON_TYPE_RESIDENT;

@Service
@RequiredArgsConstructor
public class PersonHouseServiceImpl implements IPersonHouseService {

    private final PersonHouseMapper personHouseMapper;
    private final ICommunityDetailService detailService;

    /**
     * 查询house
     *
     * @param id house主键
     * @return house
     */
    @Override
    public PersonHouse selectPersonHouseById(Long id)
    {
        return personHouseMapper.selectPersonHouseById(id);
    }

    /**
     * 查询house列表
     *
     * @param personHouse house
     * @return house
     */
    @Override
    public List<PersonHouse> selectPersonHouseList(PersonHouse personHouse)
    {
        return personHouseMapper.selectPersonHouseList(personHouse);
    }


    @Override
    public List<PersonHouse> selectByPersonIdAndType(Long personId, Long personType) {
        PersonHouse personHouse = new PersonHouse();
        personHouse.setPersonId(personId);
        personHouse.setPersonType(personType);
        return personHouseMapper.selectPersonHouseList(personHouse);
    }

    private Map<String, int[]> mapKeyIfNot(Map<String, int[]> map, String key) {
        if(!map.containsKey(key)) {
            map.put(key, new int[]{0, 0});
        }
        return map;
    }

    @Override
    @Transactional
    public void insertOrUpdateByPersonId(Long personId, Long personType, String isImportant, List<PersonHouse> houseList) {
        if(houseList != null ) {
            if(!"Y".equals(isImportant) && !"N".equals(isImportant)) {
                isImportant = "N";
            }
            boolean zyFlag = "Y".equals(isImportant);
            // 获取数据库中此人原本的地址列表
            PersonHouse p = new PersonHouse();
            p.setPersonId(personId);
            p.setPersonType(personType);
            List<PersonHouse> oldHouse = selectPersonHouseList(p);

            for (PersonHouse house : houseList) {
                house.setIsImportant(isImportant);
                if(house.getId() != null) {
                    if(house.getDelFlag() == 1) {
                        personHouseMapper.deletePersonHouseById(house.getId());
                    } else {
                        personHouseMapper.updatePersonHouse(house);
                    }
                } else {
                    house.setPersonId(personId);
                    house.setPersonType(personType);
                    personHouseMapper.insertPersonHouse(house);
                }
            }


            Set<String> newXq = houseList.stream()
                    .filter(item -> item.getDelFlag() != 1)
                    .map(PersonHouse::getCommunityCode)
                    .collect(Collectors.toSet());
            // 查询要删除的小区统计3
            Set<String> oldXq;
            if(oldHouse != null && oldHouse.size() > 0) {
                boolean changeImportant = oldHouse.get(0).getIsImportant().equals(isImportant);
                oldXq = oldHouse.stream().map(PersonHouse::getCommunityCode).collect(Collectors.toSet());
                /*
                    之所以在这重新循环一遍是为了保证数据不出现错乱
                * 循环数据库保存的房屋列表oldXq
                * 1. 如果newXq中不包含oldXq中的某个值，则代表这条数据是要被删除的， 则人口-1
                * 2. 如果newXq中包含oldXq中的某个值，则代表这条数据是要被更新的， 则人口不变再判断重点人员
                *
                * 循环要保存的房屋列表newXq, 此时只需要判断要新增的数据即可
                * 1. 如果新增则人口+1
                * */
                for (String o : oldXq) {
                    if(!newXq.contains(o)) {
                        detailService.asyncUpdatePersonCount(o, personType, -1, zyFlag? -1: 0);
                    } else if(!changeImportant) {
                        detailService.asyncUpdatePersonCount(o, personType, 0, zyFlag? 1: -1);
                    }
                }

                for (String n : newXq) {
                    if(!oldXq.contains(n)) {
                        detailService.asyncUpdatePersonCount(n, personType, 1, zyFlag? 1: 0);
                    }
                }
            } else {
                for (String n : newXq) {
                    detailService.asyncUpdatePersonCount(n, personType, 1, zyFlag? 1: 0);
                }
            }


        }
    }

    @Override
    @Transactional
    public void insertOrUpdateByPersonId(Long id, Long personType, List<PersonHouse> houseList) {
        insertOrUpdateByPersonId(id, personType, "N", houseList);
    }

    @Override
    public void deleteByPersonId(Long personId, Long personType) {
        // 获取数据库中此人原本的地址列表
        PersonHouse p = new PersonHouse();
        p.setPersonId(personId);
        p.setPersonType(personType);
        List<PersonHouse> oldHouse = selectPersonHouseList(p);
        personHouseMapper.deleteByPersonId(personId, personType);
        List<String> deleteMap = new ArrayList<>();
        for (PersonHouse house : oldHouse) {
            if(!deleteMap.contains(house.getCommunityCode())) {
                deleteMap.add(house.getCommunityCode());
                boolean zyFlag = "Y".equals(house.getIsImportant());
                detailService.asyncUpdatePersonCount(house.getCommunityCode(), personType, -1, zyFlag? -1: 0);
            }
        }

    }

    @Override
    public List<HouseAddrVo> selectHouseListGroupByMetaAddrId(HouseAddrVo addrVo) {
        return personHouseMapper.selectHouseListGroupByMetaAddrId(addrVo);
    }

}
