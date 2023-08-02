package com.jingyu.person.mapper;

import java.util.List;

import com.jingyu.person.domain.HouseAddrVo;
import com.jingyu.person.domain.PersonHouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * houseMapper接口
 * 
 * @author jingyu
 * @date 2023-07-28
 */
@Mapper
public interface PersonHouseMapper 
{
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

    /**
     * 新增house
     * 
     * @param personHouse house
     * @return 结果
     */
    public int insertPersonHouse(PersonHouse personHouse);

    /**
     * 修改house
     * 
     * @param personHouse house
     * @return 结果
     */
    public int updatePersonHouse(PersonHouse personHouse);

    /**
     * 删除house
     * 
     * @param id house主键
     * @return 结果
     */
    public int deletePersonHouseById(Long id);

    /**
     * 批量删除house
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePersonHouseByIds(Long[] ids);

    void deleteByPersonId(@Param("personId") Long personId,@Param("personType")  Long personType);

    List<HouseAddrVo> selectHouseListGroupByMetaAddrId(HouseAddrVo addrVo);

    int personIsImportant(@Param("id") Long id);
}
