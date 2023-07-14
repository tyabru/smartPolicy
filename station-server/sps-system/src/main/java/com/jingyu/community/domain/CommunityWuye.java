package com.jingyu.community.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 物业信息管理对象 sq_community_wuye
 * 
 * @author jingyu
 * @date 2023-07-14
 */
public class CommunityWuye extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 所属小区 */
    @Excel(name = "所属小区")
    private Long communityId;

    /** 工作单位 */
    @Excel(name = "工作单位")
    private String company;

    /** 工作类型 */
    @Excel(name = "工作类型")
    private String workType;

    /** 人员姓名 */
    @Excel(name = "人员姓名")
    private String name;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String certNo;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 现居地址 */
    @Excel(name = "现居地址")
    private String address;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String nativePlace;

    /** 开始工作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始工作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 是否是临时工 */
    @Excel(name = "是否是临时工")
    private String isTemp;

    /** 工作结束时间/离职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工作结束时间/离职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 是否离职 */
    @Excel(name = "是否离职")
    private String isLeaving;

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
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setWorkType(String workType) 
    {
        this.workType = workType;
    }

    public String getWorkType() 
    {
        return workType;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCertNo(String certNo) 
    {
        this.certNo = certNo;
    }

    public String getCertNo() 
    {
        return certNo;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setNativePlace(String nativePlace) 
    {
        this.nativePlace = nativePlace;
    }

    public String getNativePlace() 
    {
        return nativePlace;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setIsTemp(String isTemp) 
    {
        this.isTemp = isTemp;
    }

    public String getIsTemp() 
    {
        return isTemp;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setIsLeaving(String isLeaving) 
    {
        this.isLeaving = isLeaving;
    }

    public String getIsLeaving() 
    {
        return isLeaving;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("communityId", getCommunityId())
            .append("company", getCompany())
            .append("workType", getWorkType())
            .append("name", getName())
            .append("certNo", getCertNo())
            .append("phone", getPhone())
            .append("address", getAddress())
            .append("nativePlace", getNativePlace())
            .append("startTime", getStartTime())
            .append("isTemp", getIsTemp())
            .append("endTime", getEndTime())
            .append("isLeaving", getIsLeaving())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
