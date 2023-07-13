package com.jingyu.community.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 单位详情附件对象 sq_company_desc
 * 
 * @author jingyu
 * @date 2023-07-12
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class CompanyDesc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 小区id */
    @Excel(name = "小区id")
    private Long companyId;

    /** 附件分组标题 默认default */
    @Excel(name = "附件分组标题 默认default")
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

    private MultipartFile file;

    public CompanyDesc(Long companyId) {
        this.companyId = companyId;
    }

    public CompanyDesc(Long id, Long companyId) {
        this.id = id;
        this.companyId = companyId;
    }
}
