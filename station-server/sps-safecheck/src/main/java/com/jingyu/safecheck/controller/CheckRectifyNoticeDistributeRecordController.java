package com.jingyu.safecheck.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.safecheck.domain.CheckDangerRecord;
import com.jingyu.safecheck.domain.CheckPlaceDict;
import com.jingyu.safecheck.domain.CheckRectifyNoticeDistributeRecord;
import com.jingyu.safecheck.domain.CheckRectifyNoticeDistributeReview;
import com.jingyu.safecheck.service.ICheckPlaceDictService;
import com.jingyu.safecheck.service.ICheckRectifyNoticeDistributeRecordService;
import com.jingyu.safecheck.service.ICheckRectifyNoticeDistributeReviewService;
import com.jingyu.safecheck.utils.WordUtil;
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
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 整改通知书下发Controller
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/safecheck/rectifynoticedistribute")
public class CheckRectifyNoticeDistributeRecordController extends BaseController
{
    @Autowired
    private ICheckRectifyNoticeDistributeRecordService checkRectifyNoticeDistributeRecordService;
    @Autowired
    private ICheckPlaceDictService checkPlaceDictService;

    /**
     * 查询整改通知书下发列表
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:list')")
    @GetMapping("/list")
    public TableDataInfo list(CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord)
    {
        startPage();
        List<CheckRectifyNoticeDistributeRecord> list = checkRectifyNoticeDistributeRecordService.selectCheckRectifyNoticeDistributeRecordList(checkRectifyNoticeDistributeRecord);
        return getDataTable(list);
    }

    /**
     * 导出整改通知书下发列表
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:export')")
//    @Log(title = "整改通知书下发", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord)
//    {
//        List<CheckRectifyNoticeDistributeRecord> list = checkRectifyNoticeDistributeRecordService.selectCheckRectifyNoticeDistributeRecordList(checkRectifyNoticeDistributeRecord);
//        ExcelUtil<CheckRectifyNoticeDistributeRecord> util = new ExcelUtil<CheckRectifyNoticeDistributeRecord>(CheckRectifyNoticeDistributeRecord.class);
//        util.exportExcel(response, list, "整改通知书下发数据");
//    }

//    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:exportWord')")
    @GetMapping("/exportWord")
    public AjaxResult exportWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getQueryString().split("=")[1]);
        Map<String, Object> map = new HashMap<>();

        CheckPlaceDict checkPlaceDict = new CheckPlaceDict();
        List<CheckPlaceDict> placeDicts = checkPlaceDictService.selectCheckPlaceDictList(checkPlaceDict);
        CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord = new CheckRectifyNoticeDistributeRecord();
        List<CheckRectifyNoticeDistributeRecord> list = checkRectifyNoticeDistributeRecordService.selectCheckRectifyNoticeDistributeRecordList(checkRectifyNoticeDistributeRecord);



        for(int i=0;i<list.size();i++){
            if(list.get(i).getId()==id){
                id=i;
                break;
            }
        }
        Calendar checkCalendar = Calendar.getInstance();
        checkCalendar.setTime(list.get(id).getCheckDate());

        Calendar finishCalendar = Calendar.getInstance();
        finishCalendar.setTime(list.get(id).getFinishDate());

//        System.out.println(list.get(id).getPlaceId()-1);
//        System.out.println(placeDicts.get((int) (list.get(id).getPlaceId()-1)));
//        System.out.println(placeDicts.get((int) (list.get(id).getPlaceId()-1)).getRectifyNoticeTemplateParams());

        map.put("chected_unit", list.get(id).getChectedUnit());
        map.put("cy", checkCalendar.get(Calendar.YEAR));
        map.put("cm", checkCalendar.get(Calendar.MONTH)+1);
        map.put("cd", checkCalendar.get(Calendar.DATE));
        map.put("police", list.get(id).getCheckPolice());
        map.put("check_safty_danger", list.get(id).getCheckSaftyDanger());
        map.put("rectify_notice_template_params",placeDicts.get((int) (list.get(id).getPlaceId()-1)).getRectifyNoticeTemplateParams());
//        map.put("rectify_notice_template_params","《中华人民共和国治安管理处罚法》《陕西省消防监督检查条例》《中华人民共和国消防法》《陕西省消防监督检查条例》《企事业单位内部治安保卫条例》《公安机关监督检查企事业单位内部治安保卫工作规定》");
//        map.put("rectify_notice_template_params",placeDicts.getRectifyNoticeTemplateParams());
        map.put("finish_date_year", finishCalendar.get(Calendar.YEAR));
        map.put("finish_date_month", finishCalendar.get(Calendar.MONTH)+1);
        map.put("finish_date_day", finishCalendar.get(Calendar.DATE));

//        String str = UUID.randomUUID().toString()+".docx";
        String str = "rectifynoticeform.docx";
        //获取yml配置地址
        String tempDir = "D:\\smart-police-station\\uploadPath\\upload";
        String name = WordUtil.easyPoiExport("mapper/safecheck/static/word/整改通知书模板.docx", tempDir, str, map, request, response);
        return AjaxResult.success(name);
    }


    /**
     * 获取整改通知书下发详细信息
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(checkRectifyNoticeDistributeRecordService.selectCheckRectifyNoticeDistributeRecordById(id));
    }

    /**
     * 新增整改通知书下发
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:add')")
    @Log(title = "整改通知书下发", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord)
    {
        return toAjax(checkRectifyNoticeDistributeRecordService.insertCheckRectifyNoticeDistributeRecord(checkRectifyNoticeDistributeRecord));
    }

    /**
     * 修改整改通知书下发
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:edit')")
    @Log(title = "整改通知书下发", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord)
    {
        return toAjax(checkRectifyNoticeDistributeRecordService.updateCheckRectifyNoticeDistributeRecord(checkRectifyNoticeDistributeRecord));
    }

    /**
     * 删除整改通知书下发
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:remove')")
    @Log(title = "整改通知书下发", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(checkRectifyNoticeDistributeRecordService.deleteCheckRectifyNoticeDistributeRecordByIds(ids));
    }
}
