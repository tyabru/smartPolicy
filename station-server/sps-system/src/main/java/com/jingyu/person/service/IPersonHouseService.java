package com.jingyu.person.service;

import com.jingyu.person.domain.HouseAddrVo;
import com.jingyu.person.domain.PersonHouse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPersonHouseService {

    /**
     * 查询house
     *
     * @param id house主键
     * @return house
     */
    public PersonHouse selectPersonHouseById(Long id);

    /**
     * 查询house列表
     *
     * @param personHouse house
     * @return house集合
     */
    public List<PersonHouse> selectPersonHouseList(PersonHouse personHouse);


    List<PersonHouse> selectByPersonIdAndType(Long personId, Long personType);
    void insertOrUpdateByPersonId(Long personId, Long personType, List<PersonHouse> houseList);
    void insertOrUpdateByPersonId(Long personId, Long personType, String isImportant, List<PersonHouse> houseList);

    void deleteByPersonId(Long personId, Long personType);

    List<HouseAddrVo> selectHouseListGroupByMetaAddrId(HouseAddrVo addrVo);

}
