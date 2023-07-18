package com.jingyu.safecheck.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.safecheck.domain.CheckDangerRecord;
import com.jingyu.safecheck.service.ICheckDangerRecordService;
import com.jingyu.safecheck.utils.WordUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 安全隐患检查登记Controller
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/safecheck/checkdanger")
public class CheckDangerRecordController extends BaseController
{
    @Autowired
    private ICheckDangerRecordService checkDangerRecordService;

    /**
     * 查询安全隐患检查登记列表
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:list')")
    @GetMapping("/list")
    public TableDataInfo list(CheckDangerRecord checkDangerRecord)
    {
        startPage();
        List<CheckDangerRecord> list = checkDangerRecordService.selectCheckDangerRecordList(checkDangerRecord);
        return getDataTable(list);
    }

    /**
     * 导出安全隐患检查登记列表
     */
//    @Log(title = "根据模板导出word", businessType = BusinessType.IMPORT)
//    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:exportWord')")
//    @GetMapping("/exportWord")
//    public void wordExport(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // 注入Service层
//        checkDangerRecordService.wordExport(id, request, response);
//    }


//    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:export')")
//    @Log(title = "安全隐患检查登记", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, CheckDangerRecord checkDangerRecord) throws Exception {
//        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//        response.setCharacterEncoding("utf-8");
//    }

//    @Log(title = "根据模板导出word", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:exportWord')")
    @GetMapping("/exportWord")
    public AjaxResult exportWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getQueryString().split("=")[1]);
        Map<String, Object> map = new HashMap<>();

        CheckDangerRecord checkDangerRecord = null;
        List<CheckDangerRecord> list = checkDangerRecordService.selectCheckDangerRecordList(checkDangerRecord);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId()==id){
                id=i;
                break;
            }
        }

        Calendar checkCalendar = Calendar.getInstance();
        checkCalendar.setTime(list.get(id).getCheckDate());

        String[] res = list.get(id).getCheckItemResults().split(",");

        for(int i=0;i<res.length;i++){
            String tmp="box"+i;
            if(res[i].equals("0"))
                map.put(tmp,"否");
            else map.put(tmp,"是");
        }

        map.put("chected_unit", list.get(id).getChectedUnit());
        map.put("y", checkCalendar.get(Calendar.YEAR));
        map.put("m", checkCalendar.get(Calendar.MONTH)+1);
        map.put("d", checkCalendar.get(Calendar.DATE));
        map.put("other_safty_danger", list.get(id).getOtherSaftyDanger());


//        String str = UUID.randomUUID().toString()+".docx";
        String str = "safedangercheckform.docx";
        //获取yml配置地址
        String tempDir = "D:\\smart-police-station\\uploadPath\\upload";
        String name="";
        if(list.get(id).getPlaceId()==1){
            name = WordUtil.easyPoiExport("mapper/safecheck/static/word/加油站检查模板.docx", tempDir, str, map, request, response);
        }else if(list.get(id).getPlaceId()==2){
            name = WordUtil.easyPoiExport("mapper/safecheck/static/word/液化汽站检查模板.docx", tempDir, str, map, request, response);
        }else if(list.get(id).getPlaceId()==3){
            name = WordUtil.easyPoiExport("mapper/safecheck/static/word/培训机构检查模板.docx", tempDir, str, map, request, response);
        }else if(list.get(id).getPlaceId()==4){
            name = WordUtil.easyPoiExport("mapper/safecheck/static/word/小区检查模板.docx", tempDir, str, map, request, response);
        }else if(list.get(id).getPlaceId()==5){
            name = WordUtil.easyPoiExport("mapper/safecheck/static/word/商场超市检查模板.docx", tempDir, str, map, request, response);
        }else if(list.get(id).getPlaceId()==6){
            name = WordUtil.easyPoiExport("mapper/safecheck/static/word/宾馆检查模板.docx", tempDir, str, map, request, response);
        }else if(list.get(id).getPlaceId()==7){
            name = WordUtil.easyPoiExport("mapper/safecheck/static/word/寄递物流检查模板.docx", tempDir, str, map, request, response);
        }else if(list.get(id).getPlaceId()==8){
            name = WordUtil.easyPoiExport("mapper/safecheck/static/word/九小场所检查模板.docx", tempDir, str, map, request, response);
        }else if(list.get(id).getPlaceId()==9){
            name = WordUtil.easyPoiExport("mapper/safecheck/static/word/企事业单位检查模板.docx", tempDir, str, map, request, response);
        }else if(list.get(id).getPlaceId()==10){
            name = WordUtil.easyPoiExport("mapper/safecheck/static/word/娱乐场所检查模板.docx", tempDir, str, map, request, response);
        }else {
            name = WordUtil.easyPoiExport("mapper/safecheck/static/word/校园检查模板.docx", tempDir, str, map, request, response);
        }
        return AjaxResult.success(name);
    }

    /**
     * 获取安全隐患检查登记详细信息
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(checkDangerRecordService.selectCheckDangerRecordById(id));
    }

    /**
     * 新增安全隐患检查登记
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:add')")
    @Log(title = "安全隐患检查登记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckDangerRecord checkDangerRecord)
    {
        return toAjax(checkDangerRecordService.insertCheckDangerRecord(checkDangerRecord));
    }

    /**
     * 修改安全隐患检查登记
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:edit')")
    @Log(title = "安全隐患检查登记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckDangerRecord checkDangerRecord)
    {
        return toAjax(checkDangerRecordService.updateCheckDangerRecord(checkDangerRecord));
    }

    /**
     * 删除安全隐患检查登记
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:remove')")
    @Log(title = "安全隐患检查登记", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(checkDangerRecordService.deleteCheckDangerRecordByIds(ids));
    }
}
