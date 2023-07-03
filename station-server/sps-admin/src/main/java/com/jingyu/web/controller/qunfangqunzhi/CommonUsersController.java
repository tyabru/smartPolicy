package com.jingyu.web.controller.qunfangqunzhi;

import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.core.page.TableDataInfo;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.qunfangqunzhi.domain.CommonUser;
import com.jingyu.qunfangqunzhi.service.ICommonUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 普通用户信息Controller
 *
 * @author ruoyi
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/qf/users")
public class CommonUsersController extends BaseController
{
    @Autowired
    private ICommonUsersService commonUsersService;

    /**
     * 查询普通用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:users:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommonUser commonUser)
    {
        startPage();
        List<CommonUser> list = commonUsersService.selectCommonUsersList(commonUser);
        return getDataTable(list);
    }

    /**
     * 导出普通用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:users:export')")
    @Log(title = "普通用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommonUser commonUser)
    {
        List<CommonUser> list = commonUsersService.selectCommonUsersList(commonUser);
        ExcelUtil<CommonUser> util = new ExcelUtil<CommonUser>(CommonUser.class);
        util.exportExcel(response, list, "普通用户信息数据");
    }

    /**
     * 获取普通用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:users:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(commonUsersService.selectCommonUsersByUserId(userId));
    }

    /**
     * 新增普通用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:users:add')")
    @Log(title = "普通用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommonUser commonUser)
    {
        return toAjax(commonUsersService.insertCommonUsers(commonUser));
    }

    /**
     * 修改普通用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:users:edit')")
    @Log(title = "普通用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommonUser commonUser)
    {
        return toAjax(commonUsersService.updateCommonUsers(commonUser));
    }

    /**
     * 删除普通用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:users:remove')")
    @Log(title = "普通用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(commonUsersService.deleteCommonUsersByUserIds(userIds));
    }
}
