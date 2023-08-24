package com.jingyu.web.controller.community;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

import com.jingyu.common.config.RuoYiConfig;
import com.jingyu.common.constant.Constants;
import com.jingyu.common.core.domain.Cascader;
import com.jingyu.common.core.domain.TreeSelect;
import com.jingyu.common.exception.ExcelRowErrorInsertException;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.file.FileUtils;
import com.jingyu.community.domain.StructureImportVo;
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
    private ICommunityStructureService structureService;

    /**
     * 查询小区房屋结构和地址信息列表
     */
    @PreAuthorize("@ss.hasPermi('community:structure:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommunityStructure communityStructure)
    {
        startPage();
        List<CommunityStructure> list = structureService.selectCommunityStructureList(communityStructure);
        return getDataTable(list);
    }

    @GetMapping("room_nodes")
    public AjaxResult queryNextLevelSelector(CommunityStructure structure) {
        if(StringUtils.isEmpty(structure.getCommunityCode())) {
            return AjaxResult.success(new ArrayList<>());
        }
        List<Cascader<CommunityStructure>> treeSelects = structureService.querySelectorList(structure);
        return AjaxResult.success(treeSelects);
    }
    /**
     * 导出小区房屋结构和地址信息列表
     */
    @PreAuthorize("@ss.hasPermi('community:structure:export')")
    @Log(title = "小区房屋结构和地址信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommunityStructure communityStructure)
    {
        List<CommunityStructure> list = structureService.selectCommunityStructureList(communityStructure);
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
        return success(structureService.selectCommunityStructureById(id));
    }

    /**
     * 新增小区房屋结构和地址信息
     */
    @PreAuthorize("@ss.hasPermi('community:structure:add')")
    @Log(title = "小区房屋结构和地址信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommunityStructure communityStructure)
    {
        return toAjax(structureService.insertCommunityStructure(communityStructure));
    }

    /**
     * 修改小区房屋结构和地址信息
     */
    @PreAuthorize("@ss.hasPermi('community:structure:edit')")
    @Log(title = "小区房屋结构和地址信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommunityStructure communityStructure)
    {
        return toAjax(structureService.updateCommunityStructure(communityStructure));
    }

    /**
     * 删除小区房屋结构和地址信息
     */
    @PreAuthorize("@ss.hasPermi('community:structure:remove')")
    @Log(title = "小区房屋结构和地址信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(structureService.deleteCommunityStructureByIds(ids));
    }

    @GetMapping("downloadExample")
    public void downloadExample(HttpServletResponse response)
    {
        ExcelUtil<StructureImportVo> excelUtil = new ExcelUtil<>(StructureImportVo.class);
        excelUtil.exportExcel(response, new ArrayList<>(), "小区名字");
    }

    @Log(title = "标准地址批量导入", businessType = BusinessType.IMPORT)
    @PostMapping("importFormExcel")
    public AjaxResult importFormExcel(@RequestBody @NotEmpty(message = "导入文件不能为空！") String fileUrl) throws IOException, ExecutionException, InterruptedException {
        if (!FileUtils.checkAllowDownload(fileUrl)){
            FileUtils.deleteFileByProfileUrl(fileUrl);
            throw new RuntimeException(StringUtils.format("文件名称({})非法，不允许导入。 ", FileUtils.getName(fileUrl)));
        }
        // 本地资源路径
        String localPath = RuoYiConfig.getProfile();
        // 数据库资源地址
        File file = new File(localPath + StringUtils.substringAfter(fileUrl, Constants.RESOURCE_PREFIX));
        if(!file.exists() || !file.isFile()) {
            throw new FileNotFoundException(StringUtils.format("未找到文件({})非法，不允许导入。 ", FileUtils.getName(fileUrl)));
        }
        ExcelUtil<StructureImportVo> excelUtil = new ExcelUtil<>(StructureImportVo.class);
        InputStream in = Files.newInputStream(file.toPath());
        int i = 0, count = 0, total = 0;;
        boolean circleFlag = true;
        while (true) {
            try {
                List<StructureImportVo> list =  excelUtil.importExcel(String.valueOf(i), in, 0);
                int size = list.size();
                total += size;
                if(size > 0) {
                    count += structureService.batchInsert(list);
                    i++;
                } else {
                    break;
                }
            } catch (ExcelRowErrorInsertException e) {
                logger.error("Excel:【{}】, 在导入sheet{}时出现异常：\n ", fileUrl, i, e);
                throw new RuntimeException(e.getErrorMessage(i+1));
            } catch (Exception e) {
                String message = String.format("在导入sheet【%s】时出现异常: %s", i+1, e.getMessage());
                logger.error("Excel:【{}】, 在导入sheet{}时出现异常：\n ", fileUrl, i, e);
                throw new RuntimeException(message);
            }
        }
        in.close();
        int error = total - count;
        return AjaxResult.success("导入成功，成功【"+total+"】条， 失败【"+ (Math.max(error, 0)) +"】");
    }

    @GetMapping("loadXqxxByName")
    public AjaxResult loadXqxxByName(String queryString) {
        return AjaxResult.success(structureService.selectCommunityList(queryString));
    }
}
