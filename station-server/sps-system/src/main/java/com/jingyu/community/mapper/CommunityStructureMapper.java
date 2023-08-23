package com.jingyu.community.mapper;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import com.jingyu.community.domain.CommunityStructure;
import com.jingyu.community.domain.StructureImportVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 小区房屋结构和地址信息Mapper接口
 * 
 * @author jingyu
 * @date 2023-07-17
 */
@Mapper
public interface CommunityStructureMapper 
{
    /**
     * 查询小区房屋结构和地址信息
     * 
     * @param id 小区房屋结构和地址信息主键
     * @return 小区房屋结构和地址信息
     */
    public CommunityStructure selectCommunityStructureById(Long id);

    /**
     * 查询小区房屋结构和地址信息列表
     * 
     * @param communityStructure 小区房屋结构和地址信息
     * @return 小区房屋结构和地址信息集合
     */
    public List<CommunityStructure> selectCommunityStructureList(CommunityStructure communityStructure);

    /**
     * 新增小区房屋结构和地址信息
     * 
     * @param communityStructure 小区房屋结构和地址信息
     * @return 结果
     */
    public int insertCommunityStructure(CommunityStructure communityStructure);

    /**
     * 修改小区房屋结构和地址信息
     * 
     * @param communityStructure 小区房屋结构和地址信息
     * @return 结果
     */
    public int updateCommunityStructure(CommunityStructure communityStructure);

    /**
     * 删除小区房屋结构和地址信息
     * 
     * @param id 小区房屋结构和地址信息主键
     * @return 结果
     */
    public int deleteCommunityStructureById(Long id);

    /**
     * 批量删除小区房屋结构和地址信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommunityStructureByIds(Long[] ids);

    int batchInsertFormVo(List<StructureImportVo> list) throws SQLIntegrityConstraintViolationException;

    List<Map<String, Object>> selectCommunityList(@Param("queryString") String queryString);

    List<CommunityStructure> selectRoomListByCommunityCode(CommunityStructure structure);
}
