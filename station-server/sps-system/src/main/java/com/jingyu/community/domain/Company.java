package com.jingyu.community.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jingyu.common.core.domain.entity.SysDept;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 单位基本信息对象 sq_company
 * 
 * @author jingyu
 * @date 2023-07-12
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class Company extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 社区id */
    @Excel(name = "社区id")
    @NotNull(message = "社区id不能为空")
    private Long communityId;

    /** 单位编码 */
    @Excel(name = "单位编码")
    private String companyCode;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String companyName;

    /** 单位类型 */
    @Excel(name = "单位类型")
    private String type;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String email;

    /** 注册地址 */
    @Excel(name = "注册地址")
    private String address;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationTime;

    /** 营业执照号 */
    @Excel(name = "营业执照号")
    private String tradeCode;

    /** 组织机构代码 */
    @Excel(name = "组织机构代码")
    private String organizationCode;

    /** $column.columnComment */
    @Excel(name = "统一信用代码")
    private String creditCode;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String headMaster;

    /** 负责人联系方式 */
    @Excel(name = "负责人联系方式")
    private String contactPhone;

    /** 负责人证件号码 */
    @Excel(name = "负责人证件号码")
    private String identityCode;

    /** 证件号码类型 */
    @Excel(name = "证件号码类型")
    private String identityType;

    private String centerPoint;

    private Long police;

    private String policeName;

    private String policePhone;

    private SysDept communityObj;

    private int fileCount = 0;

    private String descIds;

    private List<CommunityDesc> descList = new ArrayList<>();

}
