package com.jingyu.web.controller.polices;

import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.core.page.TableDataInfo;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.polices.domain.PoliceFileManagements;
import com.jingyu.polices.service.IPoliceFileManagementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
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
    @GetMapping("/fileDownload/{id}")
    public AjaxResult downloadLocal(HttpServletResponse response, @PathVariable("id") Long id) {
        //默认下载文件夹
        String downloadPath = "D:/下载";
        File downloadPathFile = new File(downloadPath);
        if (!downloadPathFile.exists()) {
            // 若不存在文件夹，则创建一个文件夹
            downloadPathFile.mkdirs();
        }
        AjaxResult ajaxResult = new AjaxResult();
        PoliceFileManagements policeFileManagements = policeFileManagementsService.selectPoliceFileManagementsById(id);
        //设置文件路径
        File file = new File(filePath + policeFileManagements.getFilePath());
        //获取文件名称
        String fileName = file.getName();
        //判断文件是否存在
        if (file.exists()) {
            response.setContentType("multipart/form-data");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = new FileOutputStream(downloadPath + "/"+fileName);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
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
            }
            ajaxResult.put("code",200);
            ajaxResult.put("msg","下载成功,请前往(" + downloadPath + ")文件夹中查看");
        } else {
            ajaxResult.put("code",400);
            ajaxResult.put("code","查无文件,请确定文件路径是否正确！！");
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
