package com.jingyu.web.controller.qunfangqunzhi;

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
import com.jingyu.qunfangqunzhi.domain.CommonUsersApplication;
import com.jingyu.qunfangqunzhi.service.ICommonUsersApplicationService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 申请管理Controller
 *
 * @author jingyu
 * @date 2023-07-05
 */
@RestController
@RequestMapping("/qunfangqunzhi/applicationManage")
public class CommonUsersApplicationController extends BaseController
{
    @Autowired
    private ICommonUsersApplicationService commonUsersApplicationService;

    /**
     * 查询申请管理列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @GetMapping("/list")
    public TableDataInfo list(CommonUsersApplication commonUsersApplication)
    {
        startPage();
        List<CommonUsersApplication> list = commonUsersApplicationService.selectCommonUsersApplicationList(commonUsersApplication);
        return getDataTable(list);
    }

    /**
     * 导出申请管理列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "申请管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommonUsersApplication commonUsersApplication)
    {
        List<CommonUsersApplication> list = commonUsersApplicationService.selectCommonUsersApplicationList(commonUsersApplication);
        ExcelUtil<CommonUsersApplication> util = new ExcelUtil<CommonUsersApplication>(CommonUsersApplication.class);
        util.exportExcel(response, list, "申请管理数据");
    }

    /**
     * 获取申请管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(commonUsersApplicationService.selectCommonUsersApplicationById(id));
    }

    /**
     * 新增申请管理
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "申请管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommonUsersApplication commonUsersApplication)
    {
        return toAjax(commonUsersApplicationService.insertCommonUsersApplication(commonUsersApplication));
    }

    /**
     * 修改申请管理
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "申请管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommonUsersApplication commonUsersApplication)
    {
        return toAjax(commonUsersApplicationService.updateCommonUsersApplication(commonUsersApplication));
    }

    /**
     * 删除申请管理
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "申请管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(commonUsersApplicationService.deleteCommonUsersApplicationByIds(ids));
    }
}
