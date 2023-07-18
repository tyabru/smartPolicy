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
import com.jingyu.community.domain.CommunityStructure;
import com.jingyu.community.service.ICommunityStructureService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 小区房屋结构和地址信息Controller
 * 
 * @author jingyu
 * @date 2023-07-17
 */
@RestController
@RequestMapping("/community/structure")
public class CommunityStructureController extends BaseController
{
    @Autowired
    private ICommunityStructureService communityStructureService;

    /**
     * 查询小区房屋结构和地址信息列表
     */
    @PreAuthorize("@ss.hasPermi('community:structure:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommunityStructure communityStructure)
    {
        startPage();
        List<CommunityStructure> list = communityStructureService.selectCommunityStructureList(communityStructure);
        return getDataTable(list);
    }

    /**
     * 导出小区房屋结构和地址信息列表
     */
    @PreAuthorize("@ss.hasPermi('community:structure:export')")
    @Log(title = "小区房屋结构和地址信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommunityStructure communityStructure)
    {
        List<CommunityStructure> list = communityStructureService.selectCommunityStructureList(communityStructure);
        ExcelUtil<CommunityStructure> util = new ExcelUtil<CommunityStructure>(CommunityStructure.class);
        util.exportExcel(response, list, "小区房屋结构和地址信息数据");
    }

    /**
     * 获取小区房屋结构和地址信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('community:structure:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(communityStructureService.selectCommunityStructureById(id));
    }

    /**
     * 新增小区房屋结构和地址信息
     */
    @PreAuthorize("@ss.hasPermi('community:structure:add')")
    @Log(title = "小区房屋结构和地址信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommunityStructure communityStructure)
    {
        return toAjax(communityStructureService.insertCommunityStructure(communityStructure));
    }

    /**
     * 修改小区房屋结构和地址信息
     */
    @PreAuthorize("@ss.hasPermi('community:structure:edit')")
    @Log(title = "小区房屋结构和地址信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommunityStructure communityStructure)
    {
        return toAjax(communityStructureService.updateCommunityStructure(communityStructure));
    }

    /**
     * 删除小区房屋结构和地址信息
     */
    @PreAuthorize("@ss.hasPermi('community:structure:remove')")
    @Log(title = "小区房屋结构和地址信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(communityStructureService.deleteCommunityStructureByIds(ids));
    }
}
