package com.jingyu.community.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jingyu.common.core.domain.entity.SysDept;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 小区（村）基本信息对象 sq_community
 * 
 * @author jingyu
 * &#064;date  2023-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Community extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 小区编码
     */
    @Excel(name = "小区编码")
    @NotEmpty(message = "小区编码不能为空")
    private String code;

    /**
     * 小区名称
     */
    @Excel(name = "小区名称")
    @NotEmpty(message = "小区名称不能为空")
    private String name;

    /**
     * 0 小区 1 村
     */
    @Excel(name = "0 小区 1 村")
    @NotNull(message = "区域类型不能为空")
    private Long type;

    /**
     * 小区详细地址
     */
    @Excel(name = "小区详细地址")
    private String address;

    /**
     * 所属社区
     */
    @Excel(name = "所属社区")
    @NotNull(message = "所属社区不能为空")
    private Long community;

    private SysDept communityObj;

    /**
     * 防范措施 人防 物防 技防
     */
    @Excel(name = "防范措施")
    private Long preventiveMeasures;

    /**
     * 是否是安全小区
     */
    @Excel(name = "是否是安全小区")
    private Long isSafeArea;

    /**
     * 命名机关
     */
    @Excel(name = "命名机关")
    private String namingOrgan;

    /**
     * 命名时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "命名时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date namingTime;

    private Long pcsId;

    @Excel(name = "所属派出所")
    private String pcsName;

    private int fileCount = 0;

    private CommunityDetail detail;

    private String descIds;

    private List<CommunityDesc> descList = new ArrayList<>();

}