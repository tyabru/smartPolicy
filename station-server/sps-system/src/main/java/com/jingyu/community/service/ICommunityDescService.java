package com.jingyu.community.service;

import com.jingyu.common.exception.file.InvalidExtensionException;
import com.jingyu.community.domain.CommunityDesc;

import java.io.IOException;
import java.util.List;

public interface ICommunityDescService {

    /**
     * 查询社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param id 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理主键
     * @return 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     */
    public CommunityDesc selectCommunityDescById(Long id);

    /**
     * 查询社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理列表
     *
     * @param sqCommunityDesc 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     * @return 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理集合
     */
    public List<CommunityDesc> selectCommunityDescList(CommunityDesc sqCommunityDesc);

    /**
     * 新增社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param sqCommunityDesc 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     * @return 结果
     */
    public Long insertCommunityDesc(CommunityDesc sqCommunityDesc) throws InvalidExtensionException, IOException;

    /**
     * 修改社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param sqCommunityDesc 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     * @return 结果
     */
    public Long updateCommunityDesc(CommunityDesc sqCommunityDesc) throws IOException, InvalidExtensionException;

    /**
     * 批量删除社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param ids 需要删除的社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理主键集合
     * @return 结果
     */
    public int deleteCommunityDescByIds(Long[] ids);

    /**
     * 删除社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理信息
     *
     * @param id 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理主键
     * @return 结果
     */
    public int deleteCommunityDescById(Long id, boolean onlyNotDefined);

    void updateCommunityId(int id, Long communityId);

    void deleteByCommunityId(Long id);
}
