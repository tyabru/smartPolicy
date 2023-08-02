package com.jingyu.community.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 小区房屋结构和地址信息对象 sq_community_structure
 * 
 * @author jingyu
 * @date 2023-07-17
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CommunityStructure extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 小区编码 */
    @Excel(name = "小区编码")
    private String communityCode;

    /** 小区名称 */
    @Excel(name = "小区名称")
    private String communityName;

    /** 小区结构元数据编码 */
    @Excel(name = "小区结构元数据编码")
    private String metaAddrId;

    /** 地址全称 */
    @Excel(name = "地址全称")
    private String fullAddress;

    /** 地址级别 10 小区 11楼栋 12 单元 13 层 14 房屋 */
    @Excel(name = "地址级别", dictType="structure_level")
    private Long metaLevel;

    /** 所属派出所ID */
    private String pcsId;

    /** 所属派出所名称 */
    @Excel(name = "所属派出所")
    private String pcsName;

    /** 地址简称 */
    @Excel(name = "地址简称")
    private String shortName;

    /** 所属区域 */
    @Excel(name = "所属区域")
    private String region;

    /** 区域名称 */
    @Excel(name = "区域名称")
    private String regionName;
    @Excel(name = "楼栋")
    private String dong;
    @Excel(name = "单元")
    private String unit;
    @Excel(name = "层")
    private String ceng;
    @Excel(name = "房间号")
    private String room;
    @Excel(name = "经度")
    private Double longitude;
    @Excel(name = "纬度")
    private Double latitude;

    private Integer level;

}
