package com.jingyu.community.domain;

import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

/**
 * 文件附件
 * @author Jikang
 * */
@Data
@NoArgsConstructor
public class CommunityDesc extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 小区id */
    @Excel(name = "小区id")
    private Long communityId;

    /** 附件分组标题 默认default */
    @Excel(name = "附件分组标题 默认default")
    @NotEmpty(message = "【文件类别】不能为空！")
    private String groupTitle;

    /** 文件描述,此文件的作用 */
    @Excel(name = "文件描述,此文件的作用")
    private String fileDesc;

    /** 文件URL */
    @Excel(name = "文件URL")
    private String fileUrl;

    /** 文件后缀/文件格式 */
    @Excel(name = "文件后缀/文件格式")
    private String fileFormat;

    /** 创建用户 */
    @Excel(name = "创建用户")
    private String createUser;
    private MultipartFile file;

    public CommunityDesc(Long communityId) {
        this.communityId = communityId;
    }
}
