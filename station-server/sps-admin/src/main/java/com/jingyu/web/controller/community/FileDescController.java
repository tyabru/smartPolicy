package com.jingyu.web.controller.community;

import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.exception.file.InvalidExtensionException;
import com.jingyu.common.utils.file.FileUploadUtils;
import com.jingyu.community.domain.CommunityDesc;
import com.jingyu.community.service.ICommunityDescService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/desc")
public class FileDescController {

    private final ICommunityDescService communityDescService;

    @GetMapping("{villageId}/list")
    public AjaxResult getDescListByVillageId(@PathVariable Long villageId) {
        CommunityDesc desc = new CommunityDesc(villageId);
        return AjaxResult.success(communityDescService.selectCommunityDescList(desc));
    }

    @PostMapping("uploadFile")
    public AjaxResult uploadGroup(CommunityDesc desc) {
        Long id;
        try {
            if(desc.getId() != null) {
                communityDescService.updateCommunityDesc(desc);
                id = desc.getId();
            }else {
                id = communityDescService.insertCommunityDesc(desc);
            }

        } catch (InvalidExtensionException | IOException e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.success("保存成功", id);
    }

    @DeleteMapping("/{fileId}")
    public AjaxResult deleteById(@PathVariable Long fileId, HttpServletRequest request) {
        // 是否只删除未关联社区的数据 1 是 0 否
        String onlyNotDefined = request.getHeader("only-not-defined");
        communityDescService.deleteCommunityDescById(fileId, "1".equals(onlyNotDefined));
        return AjaxResult.success();
    }
}
