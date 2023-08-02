package com.jingyu.community.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import com.jingyu.community.mapper.CommunityDetailMapper;
import com.jingyu.community.domain.CommunityDetail;
import com.jingyu.community.service.ICommunityDetailService;

import static com.jingyu.person.PersonConstants.PERSON_TYPE_FLOW;
import static com.jingyu.person.PersonConstants.PERSON_TYPE_RESIDENT;

/**
 * 小区/村基本信息Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-07
 */
@Service
public class CommunityDetailServiceImpl implements ICommunityDetailService 
{
    @Autowired
    private CommunityDetailMapper communityDetailMapper;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 查询小区/村基本信息
     * 
     * @param id 小区/村基本信息主键
     * @return 小区/村基本信息
     */
    @Override
    public CommunityDetail selectCommunityDetailById(Long id)
    {
        return communityDetailMapper.selectCommunityDetailById(id);
    }

    @Override
    public CommunityDetail getByCodeWithLock(String communityCode) {
        return communityDetailMapper.getByCodeWithLock(communityCode);
    }

    /**
     * 查询小区/村基本信息列表
     * 
     * @param communityDetail 小区/村基本信息
     * @return 小区/村基本信息
     */
    @Override
    public List<CommunityDetail> selectCommunityDetailList(CommunityDetail communityDetail)
    {
        return communityDetailMapper.selectCommunityDetailList(communityDetail);
    }

    /**
     * 新增小区/村基本信息
     * 
     * @param communityDetail 小区/村基本信息
     * @return 结果
     */
    @Override
    public int insertCommunityDetail(CommunityDetail communityDetail)
    {
        communityDetail.setCreateTime(DateUtils.getNowDate());
        return communityDetailMapper.insertCommunityDetail(communityDetail);
    }

    /**
     * 修改小区/村基本信息
     * 
     * @param communityDetail 小区/村基本信息
     * @return 结果
     */
    @Override
    public int updateCommunityDetail(CommunityDetail communityDetail)
    {
        communityDetail.setUpdateTime(DateUtils.getNowDate());
        return communityDetailMapper.updateCommunityDetail(communityDetail);
    }

    /**
     * 批量删除小区/村基本信息
     * 
     * @param ids 需要删除的小区/村基本信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityDetailByIds(Long[] ids)
    {
        return communityDetailMapper.deleteCommunityDetailByIds(ids);
    }

    /**
     * 删除小区/村基本信息信息
     * 
     * @param id 小区/村基本信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityDetailById(Long id)
    {
        return communityDetailMapper.deleteCommunityDetailById(id);
    }

    @Override
    public void deleteByVillageId(Long id) {
        communityDetailMapper.deleteByVillageId(id);
    }

    public void asyncUpdatePersonCount(String communityCode, Long type, int count, int importCount) {
        threadPoolTaskExecutor.execute(() -> {
            updatePersonCountByHouse(communityCode, type, count, importCount);
        });
    }

    /** 更新小区表人数统计
     * @param communityCode 小区编码
     * @param type 要更新的数量类型 常驻/流动等
     * */
    public synchronized void updatePersonCountByHouse(String communityCode, Long type, int count, int importCount) {
        if(count == 0 && importCount == 0) {
            return;
        }
        CommunityDetail detail = communityDetailMapper.getByCodeWithLock(communityCode);
        if(detail != null && detail.getId() != null) {
            detail.setPerson(detail.getPerson() + count);
            if(PERSON_TYPE_RESIDENT.equals(type)) {
                detail.setResident(detail.getResident() + count);
            } else if(PERSON_TYPE_FLOW.equals(type)) {
                detail.setFlows(detail.getFlows() + count);
            }
            detail.setImportant(detail.getImportant() + importCount);
        }
        communityDetailMapper.updateCommunityDetail(detail);
    }
}
