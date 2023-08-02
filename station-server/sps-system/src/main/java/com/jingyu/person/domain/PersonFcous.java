package com.jingyu.person.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 重点关注人口对象 sq_person_fcous
 * 
 * @author jingyu
 * @date 2023-08-01
 */
public class PersonFcous extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 小区名称 */
    @Excel(name = "小区名称")
    private Long xqId;

    /** 人员类型 1 常驻人口 2 流动人员 */
    @Excel(name = "人员类型 1 常驻人口 2 流动人员")
    private Long personType;

    /** 关注原因-字典 */
    @Excel(name = "关注原因-字典")
    private String focusReason;

    /** 重点关注级别 */
    @Excel(name = "重点关注级别")
    private String level;

    /** 关注原因-补充描述 */
    @Excel(name = "关注原因-补充描述")
    private String reasonDesc;

    /** 居住地址 */
    @Excel(name = "居住地址")
    private String address;

    /** 地址编码 */
    @Excel(name = "地址编码")
    private String metaAddrId;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String nativePalce;

    /** 居住日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "居住日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date livingDate;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 人员姓名 */
    @Excel(name = "人员姓名")
    private String name;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String certType;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String certNo;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 人脸图片地址（这个或许会放到人脸库） */
    @Excel(name = "人脸图片地址", readConverterExp = "这=个或许会放到人脸库")
    private String faceImgUrl;

    /** 人脸特征值 （这个或许会放到人脸库） */
    @Excel(name = "人脸特征值 ", readConverterExp = "这=个或许会放到人脸库")
    private String features;

    /** 数据来源平台（群众自主、警员录入、网页、三方） */
    @Excel(name = "数据来源平台", readConverterExp = "群=众自主、警员录入、网页、三方")
    private String sourcePlatform;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setXqId(Long xqId) 
    {
        this.xqId = xqId;
    }

    public Long getXqId() 
    {
        return xqId;
    }
    public void setPersonType(Long personType) 
    {
        this.personType = personType;
    }

    public Long getPersonType() 
    {
        return personType;
    }
    public void setFocusReason(String focusReason) 
    {
        this.focusReason = focusReason;
    }

    public String getFocusReason() 
    {
        return focusReason;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setReasonDesc(String reasonDesc) 
    {
        this.reasonDesc = reasonDesc;
    }

    public String getReasonDesc() 
    {
        return reasonDesc;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setMetaAddrId(String metaAddrId) 
    {
        this.metaAddrId = metaAddrId;
    }

    public String getMetaAddrId() 
    {
        return metaAddrId;
    }
    public void setNativePalce(String nativePalce) 
    {
        this.nativePalce = nativePalce;
    }

    public String getNativePalce() 
    {
        return nativePalce;
    }
    public void setLivingDate(Date livingDate) 
    {
        this.livingDate = livingDate;
    }

    public Date getLivingDate() 
    {
        return livingDate;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCertType(String certType) 
    {
        this.certType = certType;
    }

    public String getCertType() 
    {
        return certType;
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
    public void setFaceImgUrl(String faceImgUrl) 
    {
        this.faceImgUrl = faceImgUrl;
    }

    public String getFaceImgUrl() 
    {
        return faceImgUrl;
    }
    public void setFeatures(String features) 
    {
        this.features = features;
    }

    public String getFeatures() 
    {
        return features;
    }
    public void setSourcePlatform(String sourcePlatform) 
    {
        this.sourcePlatform = sourcePlatform;
    }

    public String getSourcePlatform() 
    {
        return sourcePlatform;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("xqId", getXqId())
            .append("personType", getPersonType())
            .append("focusReason", getFocusReason())
            .append("level", getLevel())
            .append("reasonDesc", getReasonDesc())
            .append("address", getAddress())
            .append("metaAddrId", getMetaAddrId())
            .append("nativePalce", getNativePalce())
            .append("livingDate", getLivingDate())
            .append("nation", getNation())
            .append("name", getName())
            .append("certType", getCertType())
            .append("certNo", getCertNo())
            .append("phone", getPhone())
            .append("faceImgUrl", getFaceImgUrl())
            .append("features", getFeatures())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("sourcePlatform", getSourcePlatform())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
