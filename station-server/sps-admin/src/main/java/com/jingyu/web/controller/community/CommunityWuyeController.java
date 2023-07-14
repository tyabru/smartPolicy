package com.jingyu.web.controller.community;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.jingyu.community.domain.CommunityWuye;
import com.jingyu.community.service.ICommunityWuyeService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 物业信息管理Controller
 * 
 * @author jingyu
 * @date 2023-07-14
 */
@RestController
@RequestMapping("/wuye/wuye")
public class CommunityWuyeController extends BaseController
{
    @Autowired
    private ICommunityWuyeService communityWuyeService;

    /**
     * 查询物业信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('wuye:wuye:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommunityWuye communityWuye)
    {
        startPage();
        List<CommunityWuye> list = communityWuyeService.selectCommunityWuyeList(communityWuye);
        return getDataTable(list);
    }

    /**
     * 导出物业信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('wuye:wuye:export')")
    @Log(title = "物业信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommunityWuye communityWuye)
    {
        List<CommunityWuye> list = communityWuyeService.selectCommunityWuyeList(communityWuye);
        ExcelUtil<CommunityWuye> util = new ExcelUtil<CommunityWuye>(CommunityWuye.class);
        util.exportExcel(response, list, "物业信息管理数据");
    }

    /**
     * 获取物业信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('wuye:wuye:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(communityWuyeService.selectCommunityWuyeById(id));
    }

    /**
     * 新增物业信息管理
     */
    @PreAuthorize("@ss.hasPermi('wuye:wuye:add')")
    @Log(title = "物业信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommunityWuye communityWuye)
    {
        return toAjax(communityWuyeService.insertCommunityWuye(communityWuye));
    }

    /**
     * 修改物业信息管理
     */
    @PreAuthorize("@ss.hasPermi('wuye:wuye:edit')")
    @Log(title = "物业信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommunityWuye communityWuye)
    {
        return toAjax(communityWuyeService.updateCommunityWuye(communityWuye));
    }

    /**
     * 删除物业信息管理
     */
    @PreAuthorize("@ss.hasPermi('wuye:wuye:remove')")
    @Log(title = "物业信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(communityWuyeService.deleteCommunityWuyeByIds(ids));
    }
}
