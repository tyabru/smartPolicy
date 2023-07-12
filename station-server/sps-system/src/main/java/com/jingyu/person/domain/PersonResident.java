package com.jingyu.person.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 社区常驻人口对象 sq_person_resident
 * 
 * @author jingyu
 * @date 2023-07-10
 */
public class PersonResident extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 人员编码 */
    @Excel(name = "人员编码")
    private String bm;

    /** 社区主键id */
    @Excel(name = "社区主键id")
    private Long xqId;

    /** 人员姓名 */
    @Excel(name = "人员姓名")
    private String name;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String certNo;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 居住地址 */
    @Excel(name = "居住地址")
    private String address;

    /** 地址编码 */
    @Excel(name = "地址编码")
    private String metaAddrId;

    /** 与房东关系（本人、家人等） */
    @Excel(name = "与房东关系", readConverterExp = "本=人、家人等")
    private Long identity;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String nativePlace;

    /** 入住日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入住日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date livingDate;

    /** 人脸图片地址（这个或许会放到人脸库） */
    @Excel(name = "人脸图片地址", readConverterExp = "这=个或许会放到人脸库")
    private String faceImgUrl;

    /** 人脸特征值 （这个或许会放到人脸库） */
    private String features;

    /** 公司 */
    @Excel(name = "公司")
    private String company;

    /** 职业 */
    @Excel(name = "职业")
    private String career;

    /** 是否是重点人员 */
    @Excel(name = "是否是重点人员")
    private Long isImportant;

    /** 关注的级别 0绿 1黄 2红 */
    @Excel(name = "关注的级别 0绿 1黄 2红")
    private String attentionLevel;

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
    public void setBm(String bm) 
    {
        this.bm = bm;
    }

    public String getBm() 
    {
        return bm;
    }
    public void setXqId(Long xqId) 
    {
        this.xqId = xqId;
    }

    public Long getXqId() 
    {
        return xqId;
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
    public void setMetaAddrId(String metaAddrId) 
    {
        this.metaAddrId = metaAddrId;
    }

    public String getMetaAddrId() 
    {
        return metaAddrId;
    }
    public void setIdentity(Long identity) 
    {
        this.identity = identity;
    }

    public Long getIdentity() 
    {
        return identity;
    }
    public void setNativePlace(String nativePlace) 
    {
        this.nativePlace = nativePlace;
    }

    public String getNativePlace() 
    {
        return nativePlace;
    }
    public void setLivingDate(Date livingDate) 
    {
        this.livingDate = livingDate;
    }

    public Date getLivingDate() 
    {
        return livingDate;
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
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setCareer(String career) 
    {
        this.career = career;
    }

    public String getCareer() 
    {
        return career;
    }
    public void setIsImportant(Long isImportant) 
    {
        this.isImportant = isImportant;
    }

    public Long getIsImportant() 
    {
        return isImportant;
    }
    public void setAttentionLevel(String attentionLevel) 
    {
        this.attentionLevel = attentionLevel;
    }

    public String getAttentionLevel() 
    {
        return attentionLevel;
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
            .append("bm", getBm())
            .append("xqId", getXqId())
            .append("name", getName())
            .append("certNo", getCertNo())
            .append("phone", getPhone())
            .append("address", getAddress())
            .append("metaAddrId", getMetaAddrId())
            .append("identity", getIdentity())
            .append("nativePlace", getNativePlace())
            .append("livingDate", getLivingDate())
            .append("faceImgUrl", getFaceImgUrl())
            .append("features", getFeatures())
            .append("company", getCompany())
            .append("career", getCareer())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("isImportant", getIsImportant())
            .append("attentionLevel", getAttentionLevel())
            .append("sourcePlatform", getSourcePlatform())
            .toString();
    }
}
