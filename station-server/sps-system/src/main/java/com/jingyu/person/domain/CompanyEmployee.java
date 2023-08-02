package com.jingyu.person.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 单位员工对象 sq_company_employee
 * 
 * @author jingyu
 * @date 2023-07-21
 */
public class CompanyEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 所属单位 */
    @Excel(name = "所属单位")
    private Long companyId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String identityType;

    /** 证件编号 */
    @Excel(name = "证件编号")
    private String identityCode;

    /** 居住地址 */
    @Excel(name = "居住地址")
    private String livingAddres;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String nativePalce;

    /** 职位类型 */
    @Excel(name = "职位类型")
    private String positionType;

    /** 入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinedDate;

    /** 人像图片 */
    @Excel(name = "人像图片")
    private String faceImgUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setIdentityType(String identityType) 
    {
        this.identityType = identityType;
    }

    public String getIdentityType() 
    {
        return identityType;
    }
    public void setIdentityCode(String identityCode) 
    {
        this.identityCode = identityCode;
    }

    public String getIdentityCode() 
    {
        return identityCode;
    }
    public void setLivingAddres(String livingAddres) 
    {
        this.livingAddres = livingAddres;
    }

    public String getLivingAddres() 
    {
        return livingAddres;
    }
    public void setNativePalce(String nativePalce) 
    {
        this.nativePalce = nativePalce;
    }

    public String getNativePalce() 
    {
        return nativePalce;
    }
    public void setPositionType(String positionType) 
    {
        this.positionType = positionType;
    }

    public String getPositionType() 
    {
        return positionType;
    }
    public void setJoinedDate(Date joinedDate) 
    {
        this.joinedDate = joinedDate;
    }

    public Date getJoinedDate() 
    {
        return joinedDate;
    }
    public void setFaceImgUrl(String faceImgUrl) 
    {
        this.faceImgUrl = faceImgUrl;
    }

    public String getFaceImgUrl() 
    {
        return faceImgUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("identityType", getIdentityType())
            .append("identityCode", getIdentityCode())
            .append("livingAddres", getLivingAddres())
            .append("nativePalce", getNativePalce())
            .append("positionType", getPositionType())
            .append("joinedDate", getJoinedDate())
            .append("faceImgUrl", getFaceImgUrl())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
