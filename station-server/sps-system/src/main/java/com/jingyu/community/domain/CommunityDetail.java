package com.jingyu.community.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

import javax.validation.constraints.NotEmpty;

/**
 * 小区/村基本信息对象 sq_community_detail
 * 
 * @author jingyu
 * @date 2023-07-07
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CommunityDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 小区编码 */
    @Excel(name = "社区编码")
    @NotEmpty
    private Long communityId;

    /** 关联小区id */
    @Excel(name = "关联小区id")
    @NotEmpty
    private Long villageId;

    /** 关联小区编码 */
    @Excel(name = "关联小区编码")
    private String villageCode;

    /** 总面积 */
    @Excel(name = "总面积")
    private BigDecimal fullArea;

    /** 小区中心坐标 【经度，维度】 */
    @Excel(name = "小区中心坐标 【经度，维度】")
    private String centerPoint;

    /** 小区别界坐标集合 用于地图画区 */
    @Excel(name = "小区别界坐标集合 用于地图画区")
    private String xqRegion;

    /** 小区包含楼栋数 */
    @Excel(name = "小区包含楼栋数")
    private Long buildingCount;

    /** 总人口数 */
    @Excel(name = "总人口数")
    private int person = 0;

    /** 常驻人口数 */
    @Excel(name = "常驻人口数")
    private int resident = 0;

    /** 重点关注人口数 */
    @Excel(name = "重点关注人口数")
    private int important = 0;

    /** 负责人 */
    @Excel(name = "负责人")
    private String headMaster;

    /** 负责人联系电话 */
    @Excel(name = "负责人联系电话")
    private String masterPhone;

    /** 负责民警 */
    @Excel(name = "负责民警")
    private String police;

    /** 民警联系电话 */
    @Excel(name = "民警联系电话")
    private String policePhone;

    /** 负责民警姓名 */
    @Excel(name = "负责民警姓名")
    private String policeName;

    public CommunityDetail() {
    }

    public CommunityDetail(Long villageId) {
        this.villageId = villageId;
    }
}
