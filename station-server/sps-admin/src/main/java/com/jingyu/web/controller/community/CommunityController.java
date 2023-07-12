package com.jingyu.web.controller.community;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.common.constant.DeptConstants;
import com.jingyu.common.core.domain.entity.SysDept;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.system.service.ISysDeptService;
import com.jingyu.system.service.impl.SysDeptServiceImpl;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.community.domain.Community;
import com.jingyu.community.service.ICommunityService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 小区（村）基本信息Controller
 * 
 * @author jingyu
 * @date 2023-07-03
 */
@RestController
@RequestMapping("/community/community")
public class CommunityController extends BaseController
{
    @Autowired
    private ICommunityService communityService;
    @Autowired
    private ISysDeptService deptService;

    /**
     * 查询小区（村）基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('community:community:list')")
    @GetMapping("/list")
    public TableDataInfo list(Community Community)
    {
        startPage();
        List<Community> list = communityService.selectSqCommunityList(Community);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('community:community:list')")
    @GetMapping("/listByRole")
    public AjaxResult listByRole(Community community)
    {
        val loginUser = SecurityUtils.getLoginUser();
        Long deptId = loginUser.getDeptId();
        String ids = deptService.queryChildIdsByTypeAndDeptId(deptId, DeptConstants.POLICE_COMMUNITY_AREA);
        if(StringUtils.isEmpty(ids)) {
            return AjaxResult.success(new ArrayList<>());
        }
        community.getParams().put("sqIds", ids.split(","));
        List<Community> list = communityService.selectSqCommunityList(community);
        return AjaxResult.success(list);
    }

    /**
     * 导出小区（村）基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('community:community:export')")
    @Log(title = "小区（村）基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Community Community)
    {
        List<Community> list = communityService.selectSqCommunityList(Community);
        ExcelUtil<Community> util = new ExcelUtil<Community>(Community.class);
        util.exportExcel(response, list, "小区（村）基本信息数据");
    }

    /**
     * 获取小区（村）基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('community:community:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(communityService.selectSqCommunityById(id));
    }

    /**
     * 新增小区（村）基本信息
     */
    @PreAuthorize("@ss.hasPermi('community:community:add')")
    @Log(title = "小区（村）基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Community Community)
    {
        return toAjax(communityService.insertSqCommunity(Community));
    }

    /**
     * 修改小区（村）基本信息
     */
    @PreAuthorize("@ss.hasPermi('community:community:edit')")
    @Log(title = "小区（村）基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Community Community)
    {
        return toAjax(communityService.updateSqCommunity(Community));
    }

    /**
     * 删除小区（村）基本信息
     */
    @PreAuthorize("@ss.hasPermi('community:community:remove')")
    @Log(title = "小区（村）基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(communityService.deleteSqCommunityByIds(ids));
    }
}
