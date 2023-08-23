package com.jingyu.community.mapper;

import java.util.List;
import com.jingyu.community.domain.CommunityDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 小区/村基本信息Mapper接口
 * 
 * @author jingyu
 * @date 2023-07-07
 */
@Mapper
public interface CommunityDetailMapper 
{
    /**
     * 查询小区/村基本信息
     * 
     * @param id 小区/村基本信息主键
     * @return 小区/村基本信息
     */
    public CommunityDetail selectCommunityDetailById(Long id);

    public CommunityDetail selectByVillageId(@Param("villageId") Long villageId);

    /**
     * 查询小区/村基本信息列表
     * 
     * @param communityDetail 小区/村基本信息
     * @return 小区/村基本信息集合
     */
    public List<CommunityDetail> selectCommunityDetailList(CommunityDetail communityDetail);

    /**
     * 新增小区/村基本信息
     * 
     * @param communityDetail 小区/村基本信息
     * @return 结果
     */
    public int insertCommunityDetail(CommunityDetail communityDetail);

    /**
     * 修改小区/村基本信息
     * 
     * @param communityDetail 小区/村基本信息
     * @return 结果
     */
    public int updateCommunityDetail(CommunityDetail communityDetail);

    /**
     * 删除小区/村基本信息
     * 
     * @param id 小区/村基本信息主键
     * @return 结果
     */
    public int deleteCommunityDetailById(Long id);

    /**
     * 批量删除小区/村基本信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommunityDetailByIds(Long[] ids);

    void deleteByVillageId(Long id);

    CommunityDetail getByCodeWithLock(@Param("communityCode") String communityCode);
}
