package com.jingyu.web.controller.polices;

import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.core.page.TableDataInfo;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.polices.domain.PoliceFileManagements;
import com.jingyu.polices.service.IPoliceFileManagementsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * 日常文件管理Controller
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/polices/fileManagements")
public class PoliceFileManagementsController extends BaseController
{
    @Value("${jingyu.profile}")
    private String filePath;

    @Resource(name = "policeFileManagementsServiceImpl")
    private IPoliceFileManagementsService policeFileManagementsService;

    /**
     * 查询日常文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('polices:fileManagements:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoliceFileManagements policeFileManagements)
    {
        startPage();
        List<PoliceFileManagements> list = policeFileManagementsService.selectPoliceFileManagementsList(policeFileManagements);
        return getDataTable(list);
    }

    /**
     * 导出日常文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('polices:fileManagements:export')")
    @Log(title = "日常文件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PoliceFileManagements policeFileManagements)
    {
        List<PoliceFileManagements> list = policeFileManagementsService.selectPoliceFileManagementsList(policeFileManagements);
        ExcelUtil<PoliceFileManagements> util = new ExcelUtil<PoliceFileManagements>(PoliceFileManagements.class);
        util.exportExcel(response, list, "日常文件管理数据");
    }

    /**
     * 获取日常文件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('polices:fileManagements:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(policeFileManagementsService.selectPoliceFileManagementsById(id));
    }

    /**
     * 新增日常文件管理
     */
    @PreAuthorize("@ss.hasPermi('polices:fileManagements:add')")
    @Log(title = "日常文件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoliceFileManagements policeFileManagements)
    {
        return toAjax(policeFileManagementsService.insertPoliceFileManagements(policeFileManagements));
    }

    /**
     * 文件上传
     * */
    @PostMapping(value = "/fileUpload")
    public AjaxResult imgUpload(HttpServletRequest req, MultipartHttpServletRequest multiReq) throws IOException {
        String profilePath = multiReq.getHeader("commonFilePath");
        String folderName = multiReq.getHeader("folderName");
        String deptId = multiReq.getHeader("deptId");
        String path = "/" + profilePath + "/" + deptId + "/" + folderName;
        String fileUrlPath = filePath + "/" + path;
        File localFile = new File(fileUrlPath);
        if (!localFile.exists()) {
            // 若不存在文件夹，则创建一个文件夹
            localFile.mkdirs();
        }
        MultipartFile file = multiReq.getFile("file");
        //获取图片原文件名
        String originalFilename = file.getOriginalFilename();
        File filePathUrl = new File(fileUrlPath + "/" + originalFilename);
        file.transferTo(filePathUrl);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("code", 200);
        ajaxResult.put("filePath", path + "/" + originalFilename);
        ajaxResult.put("fileName", originalFilename);
        return ajaxResult;
    }

    /**
     * 文件下载
     * */
    @GetMapping( "/downloadFile/{id}" )
    public AjaxResult downloadFile(@PathVariable("id") Long id, HttpServletResponse response) {
        PoliceFileManagements policeFileManagements = policeFileManagementsService.selectPoliceFileManagementsById(id);

        response.setContentType("application/octet-stream");
        response.setHeader("content-disposition", "attachment;filename=" + policeFileManagements.getFileName());
        response.setCharacterEncoding("UTF-8");
        AjaxResult ajaxResult = new AjaxResult();
        String downPath = filePath + "/download";
        //默认下载文件夹
        File downloadPathFile = new File(downPath);
        if (!downloadPathFile.exists()) {
            // 若不存在文件夹，则创建一个文件夹
            downloadPathFile.mkdirs();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream fis = null;
        //判断文件是否存在
        byte[] buffer = new byte[1024];
        try {
            FileInputStream inputStream = new FileInputStream(filePath + policeFileManagements.getFilePath());
            bis = new BufferedInputStream(inputStream);
            fis = new BufferedOutputStream(response.getOutputStream());
            int i = bis.read(buffer);
            while (i != -1) {
                fis.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.put("code",400);
            ajaxResult.put("msg","下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ajaxResult.put("code",200);
            ajaxResult.put("msg","下载成功,请前往" + downPath + "文件夹查看");
        }
        return ajaxResult;
    }



    /**
     * 修改日常文件管理
     */
    @PreAuthorize("@ss.hasPermi('polices:fileManagements:edit')")
    @Log(title = "日常文件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoliceFileManagements policeFileManagements)
    {
        return toAjax(policeFileManagementsService.updatePoliceFileManagements(policeFileManagements));
    }

    /**
     * 删除日常文件管理
     */
    @PreAuthorize("@ss.hasPermi('polices:fileManagements:remove')")
    @Log(title = "日常文件管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(policeFileManagementsService.deletePoliceFileManagementsByIds(ids));
    }
}
