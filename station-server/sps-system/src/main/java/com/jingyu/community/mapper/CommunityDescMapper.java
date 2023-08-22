package com.jingyu.community.mapper;

import com.jingyu.community.domain.CommunityDesc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityDescMapper {

    /**
     * 查询社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param id 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理主键
     * @return 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     */
    public CommunityDesc selectSqCommunityDescById(Long id);

    /**
     * 查询社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理列表
     *
     * @param sqCommunityDesc 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     * @return 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理集合
     */
    public List<CommunityDesc> selectSqCommunityDescList(CommunityDesc sqCommunityDesc);

    /**
     * 新增社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param sqCommunityDesc 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     * @return 结果
     */
    public int insertSqCommunityDesc(CommunityDesc sqCommunityDesc);

    /**
     * 修改社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param sqCommunityDesc 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     * @return 结果
     */
    public int updateSqCommunityDesc(CommunityDesc sqCommunityDesc);

    /**
     * 删除社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param id 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理主键
     * @return 结果
     */
    public int deleteSqCommunityDescById(Long id);

    /**
     * 批量删除社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSqCommunityDescByIds(Long[] ids);

    void updateCommunityId(@Param("id") long id,@Param("communityId") Long communityId);

    void deleteByCommunityId(Long id);
}
