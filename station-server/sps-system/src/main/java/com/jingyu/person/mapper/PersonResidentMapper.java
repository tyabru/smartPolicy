package com.jingyu.person.mapper;

import java.util.List;
import com.jingyu.person.domain.PersonResident;
import org.apache.ibatis.annotations.Mapper;

/**
 * 社区常驻人口Mapper接口
 * 
 * @author jingyu
 * @date 2023-07-10
 */
@Mapper
public interface PersonResidentMapper 
{
    /**
     * 查询社区常驻人口
     * 
     * @param id 社区常驻人口主键
     * @return 社区常驻人口
     */
    public PersonResident selectPersonResidentById(Long id);

    /**
     * 查询社区常驻人口列表
     * 
     * @param personResident 社区常驻人口
     * @return 社区常驻人口集合
     */
    public List<PersonResident> selectPersonResidentList(PersonResident personResident);

    /**
     * 新增社区常驻人口
     * 
     * @param personResident 社区常驻人口
     * @return 结果
     */
    public int insertPersonResident(PersonResident personResident);

    /**
     * 修改社区常驻人口
     * 
     * @param personResident 社区常驻人口
     * @return 结果
     */
    public int updatePersonResident(PersonResident personResident);

    /**
     * 删除社区常驻人口
     * 
     * @param id 社区常驻人口主键
     * @return 结果
     */
    public int deletePersonResidentById(Long id);

    /**
     * 批量删除社区常驻人口
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePersonResidentByIds(Long[] ids);

    /**
     * 查询社区常驻人口
     *
     * @param certNo 人员身份证号码
     * @return 社区常驻人口
     */
    public PersonResident selectPersonResidentByCerNo(String certNo);
}
