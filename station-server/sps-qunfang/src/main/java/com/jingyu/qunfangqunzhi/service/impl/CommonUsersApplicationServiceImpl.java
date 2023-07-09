package com.jingyu.qunfangqunzhi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.qunfangqunzhi.mapper.CommonUsersApplicationMapper;
import com.jingyu.qunfangqunzhi.domain.CommonUsersApplication;
import com.jingyu.qunfangqunzhi.service.ICommonUsersApplicationService;

/**
 * 申请管理Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-05
 */
@Service
public class CommonUsersApplicationServiceImpl implements ICommonUsersApplicationService 
{
    @Autowired
    private CommonUsersApplicationMapper commonUsersApplicationMapper;

    /**
     * 查询申请管理
     * 
     * @param id 申请管理主键
     * @return 申请管理
     */
    @Override
    public CommonUsersApplication selectCommonUsersApplicationById(Long id)
    {
        return commonUsersApplicationMapper.selectCommonUsersApplicationById(id);
    }

    /**
     * 查询申请管理列表
     * 
     * @param commonUsersApplication 申请管理
     * @return 申请管理
     */
    @Override
    public List<CommonUsersApplication> selectCommonUsersApplicationList(CommonUsersApplication commonUsersApplication)
    {
        return commonUsersApplicationMapper.selectCommonUsersApplicationList(commonUsersApplication);
    }

    /**
     * 新增申请管理
     * 
     * @param commonUsersApplication 申请管理
     * @return 结果
     */
    @Override
    public int insertCommonUsersApplication(CommonUsersApplication commonUsersApplication)
    {
        return commonUsersApplicationMapper.insertCommonUsersApplication(commonUsersApplication);
    }

    /**
     * 修改申请管理
     * 
     * @param commonUsersApplication 申请管理
     * @return 结果
     */
    @Override
    public int updateCommonUsersApplication(CommonUsersApplication commonUsersApplication)
    {
        return commonUsersApplicationMapper.updateCommonUsersApplication(commonUsersApplication);
    }

    /**
     * 批量删除申请管理
     * 
     * @param ids 需要删除的申请管理主键
     * @return 结果
     */
    @Override
    public int deleteCommonUsersApplicationByIds(Long[] ids)
    {
        return commonUsersApplicationMapper.deleteCommonUsersApplicationByIds(ids);
    }

    /**
     * 删除申请管理信息
     * 
     * @param id 申请管理主键
     * @return 结果
     */
    @Override
    public int deleteCommonUsersApplicationById(Long id)
    {
        return commonUsersApplicationMapper.deleteCommonUsersApplicationById(id);
    }
}
