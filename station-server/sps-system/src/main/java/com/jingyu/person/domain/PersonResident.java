package com.jingyu.person.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 社区常驻人口对象 sq_person_resident
 * 
 * @author jingyu
 * @date 2023-07-19
 */
@Data
public class PersonResident extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 人员编码 */
    @Excel(name = "人员编码")
    private String bm;

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

    /** 居住地址 */
    @Excel(name = "居住地址")
    private String address;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String nativePlace;

    /** 人脸图片地址（这个或许会放到人脸库） */
    @Excel(name = "人脸图片地址", readConverterExp = "这=个或许会放到人脸库")
    private String faceImgUrl;

    /** 人脸特征值 （这个或许会放到人脸库） */
    @Excel(name = "人脸特征值 ", readConverterExp = "这=个或许会放到人脸库")
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

}
