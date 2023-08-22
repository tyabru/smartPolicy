package com.jingyu.web.controller.qunfangqunzhi;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.qunfangqunzhi.domain.AllocateRectifyContent;
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
import com.jingyu.qunfangqunzhi.service.IAllocateRectifyContentService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 下发任务修改Controller
 *
 * @author jingyu
 * @date 2023-07-26
 */
@RestController
@RequestMapping("/qf/allocateRectify")
public class AllocateRectifyContentController extends BaseController
{
    @Autowired
    private IAllocateRectifyContentService allocateRectifyContentService;

    /**
     * 查询下发任务修改列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @GetMapping("/list")
    public TableDataInfo list(AllocateRectifyContent allocateRectifyContent)
    {
        startPage();
        List<AllocateRectifyContent> list = allocateRectifyContentService.selectAllocateRectifyContentList(allocateRectifyContent);
        return getDataTable(list);
    }

    /**
     * 导出下发任务修改列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "下发任务修改", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AllocateRectifyContent allocateRectifyContent)
    {
        List<AllocateRectifyContent> list = allocateRectifyContentService.selectAllocateRectifyContentList(allocateRectifyContent);
        ExcelUtil<AllocateRectifyContent> util = new ExcelUtil<AllocateRectifyContent>(AllocateRectifyContent.class);
        util.exportExcel(response, list, "下发任务修改数据");
    }

    /**
     * 获取下发任务修改详细信息
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(allocateRectifyContentService.selectAllocateRectifyContentById(id));
    }

    /**
     * 新增下发任务修改
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "下发任务修改", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AllocateRectifyContent allocateRectifyContent)
    {
        return toAjax(allocateRectifyContentService.insertAllocateRectifyContent(allocateRectifyContent));
    }

    /**
     * 修改下发任务修改
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "下发任务修改", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AllocateRectifyContent allocateRectifyContent)
    {
        return toAjax(allocateRectifyContentService.updateAllocateRectifyContent(allocateRectifyContent));
    }

    /**
     * 删除下发任务修改
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "下发任务修改", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(allocateRectifyContentService.deleteAllocateRectifyContentByIds(ids));
    }
}
