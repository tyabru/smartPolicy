package com.jingyu.community.service.impl;

import java.util.Arrays;
import java.util.List;

import com.jingyu.common.exception.base.BaseException;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.sign.AESUtil;
import com.jingyu.community.domain.Community;
import com.jingyu.community.domain.CommunityDetail;
import com.jingyu.community.mapper.CommunityMapper;
import com.jingyu.community.service.ICommunityDescService;
import com.jingyu.community.service.ICommunityDetailService;
import com.jingyu.community.service.ICommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 小区（村）基本信息Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-03
 */
@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements ICommunityService
{
    private final CommunityMapper communityMapper;
    private final ICommunityDescService descService;
    private final ICommunityDetailService detailService;

    /**
     * 查询小区（村）基本信息
     * 
     * @param id 小区（村）基本信息主键
     * @return 小区（村）基本信息
     */
    @Override
    public Community selectSqCommunityById(Long id)
    {
        return communityMapper.selectSqCommunityById(id);
    }

    /**
     * 查询小区（村）基本信息列表
     * 
     * @param community 小区（村）基本信息
     * @return 小区（村）基本信息
     */
    @Override
    public List<Community> selectSqCommunityList(Community community)
    {
        List<Community> communities = communityMapper.selectSqCommunityList(community);
        for (Community community1 : communities) {
            CommunityDetail detail = community1.getDetail();
            detail.setPolicePhone(AESUtil.decrypt(detail.getPolicePhone()));
        }
        return communities;
    }

    /**
     * 新增小区（村）基本信息
     * 
     * @param community 小区（村）基本信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSqCommunity(Community community)
    {
        String username = SecurityUtils.getUsername();
        community.setCreateTime(DateUtils.getNowDate());
        community.setCreateBy(username);
        if(codeIsExists(community.getCode())) {
            throw new BaseException("小区编码已存在!");
        }
        int i = communityMapper.insertSqCommunity(community);
        if(i > 0) {
            String ids = community.getDescIds();
            if(StringUtils.isNotEmpty(ids)) {
                long[] idArr = Arrays.stream(ids.split(",")).mapToLong(Long::parseLong).toArray();
                for (long id : idArr) {
                    descService.updateCommunityId(id, community.getId());
                }
            }
            CommunityDetail detail = community.getDetail();
            detail.setCommunityId(community.getCommunity());
            detail.setVillageId(community.getId());
            detail.setVillageCode(community.getCode());
            List<CommunityDetail> tempList = detailService.selectCommunityDetailList(new CommunityDetail(community.getId()));
            if(tempList != null && tempList.size() > 0) {
                detail.setId(tempList.get(0).getId());
                detail.setUpdateBy(username);
                detailService.updateCommunityDetail(detail);
            } else {
                detail.setCreateBy(username);
                detailService.insertCommunityDetail(detail);
            }
        }
        return i;
    }

    /**
     * 修改小区（村）基本信息
     * 
     * @param community 小区（村）基本信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateSqCommunity(Community community)
    {
        String username = SecurityUtils.getUsername();
        if(codeIsExistsExcludeSelf(community.getId(), community.getCode())) {
            throw new BaseException("小区编码已存在!");
        }
        community.setUpdateBy(username);
        int i = communityMapper.updateSqCommunity(community);
        if(i > 0) {
            String ids = community.getDescIds();
            if(StringUtils.isNotEmpty(ids)) {
                int[] idArr = Arrays.stream(ids.split(",")).mapToInt(Integer::parseInt).toArray();
                for (int id : idArr) {
                    descService.updateCommunityId(id, community.getId());
                }
            }
            CommunityDetail detail = community.getDetail();
            detail.setCommunityId(community.getCommunity());
            detail.setVillageId(community.getId());
            detail.setVillageCode(community.getCode());

            List<CommunityDetail> tempList = detailService.selectCommunityDetailList(new CommunityDetail(community.getId()));
            if(tempList != null && tempList.size() > 0) {
                detail.setId(tempList.get(0).getId());
                detail.setUpdateBy(username);
                detailService.updateCommunityDetail(detail);
            } else {
                detail.setCreateBy(username);
                detailService.insertCommunityDetail(detail);
            }
        }
        return i;
    }

    /**
     * 批量删除小区（村）基本信息
     * 
     * @param ids 需要删除的小区（村）基本信息主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteSqCommunityByIds(Long[] ids)
    {

        for (Long id : ids) {
            descService.deleteByCommunityId(id);
            detailService.deleteByVillageId(id);
            communityMapper.deleteSqCommunityById(id);
        }
        return ids.length;
    }

    /**
     * 删除小区（村）基本信息信息
     * 
     * @param id 小区（村）基本信息主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteSqCommunityById(Long id)
    {
        descService.deleteByCommunityId(id);
        detailService.deleteByVillageId(id);
        return communityMapper.deleteSqCommunityById(id);
    }

    public boolean codeIsExistsExcludeSelf(Long id, String code) {
        if(StringUtils.isEmpty(code)) {
            // 如果code为空, 则默认为true
            return true;
        }
        Community community = new Community();
        community.setCode(code);
        community.getParams().put("excludeId", id);
        int count = communityMapper.countByCondition(community);
        return count > 0;
    }

    @Override
    public boolean codeIsExists(String code) {
        if(StringUtils.isEmpty(code)) {
            // 如果code为空, 则默认为true
            return true;
        }
        Community community = new Community();
        community.setCode(code);
        int count = communityMapper.countByCondition(community);
        return count > 0;
    }


}
