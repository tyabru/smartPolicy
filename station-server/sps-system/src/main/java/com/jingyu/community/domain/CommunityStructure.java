package com.jingyu.community.domain;

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
public class CommunityStructure extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 小区外键 */
    @Excel(name = "小区外键")
    private Long communityId;

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
    @Excel(name = "地址级别 10 小区 11楼栋 12 单元 13 层 14 房屋")
    private Long metaLevel;

    /** 上级编码 */
    @Excel(name = "上级编码")
    private Long parentId;

    /** 所属部门 */
    @Excel(name = "所属部门")
    private String deptId;

    /** 地址简称 */
    @Excel(name = "地址简称")
    private String shortName;

    /** 所属区域 */
    @Excel(name = "所属区域")
    private String region;

    /** 区域名称 */
    @Excel(name = "区域名称")
    private String regionName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCommunityId(Long communityId) 
    {
        this.communityId = communityId;
    }

    public Long getCommunityId() 
    {
        return communityId;
    }
    public void setCommunityCode(String communityCode) 
    {
        this.communityCode = communityCode;
    }

    public String getCommunityCode() 
    {
        return communityCode;
    }
    public void setCommunityName(String communityName) 
    {
        this.communityName = communityName;
    }

    public String getCommunityName() 
    {
        return communityName;
    }
    public void setMetaAddrId(String metaAddrId) 
    {
        this.metaAddrId = metaAddrId;
    }

    public String getMetaAddrId() 
    {
        return metaAddrId;
    }
    public void setFullAddress(String fullAddress) 
    {
        this.fullAddress = fullAddress;
    }

    public String getFullAddress() 
    {
        return fullAddress;
    }
    public void setMetaLevel(Long metaLevel) 
    {
        this.metaLevel = metaLevel;
    }

    public Long getMetaLevel() 
    {
        return metaLevel;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setDeptId(String deptId) 
    {
        this.deptId = deptId;
    }

    public String getDeptId() 
    {
        return deptId;
    }
    public void setShortName(String shortName) 
    {
        this.shortName = shortName;
    }

    public String getShortName() 
    {
        return shortName;
    }
    public void setRegion(String region) 
    {
        this.region = region;
    }

    public String getRegion() 
    {
        return region;
    }
    public void setRegionName(String regionName) 
    {
        this.regionName = regionName;
    }

    public String getRegionName() 
    {
        return regionName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("communityId", getCommunityId())
            .append("communityCode", getCommunityCode())
            .append("communityName", getCommunityName())
            .append("metaAddrId", getMetaAddrId())
            .append("fullAddress", getFullAddress())
            .append("metaLevel", getMetaLevel())
            .append("parentId", getParentId())
            .append("deptId", getDeptId())
            .append("shortName", getShortName())
            .append("region", getRegion())
            .append("regionName", getRegionName())
            .toString();
    }
}
