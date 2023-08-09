package com.jingyu.person.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jingyu.common.utils.encryption_decryption.SensitiveNew;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonFcous extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 小区ID */
    @Excel(name = "小区ID")
    private Long xqId;

    /** 小区名称 */
    @Excel(name = "小区名称")
    private String communityName;

    /** 人员类型 1 常驻人口 2 流动人员 */
    @Excel(name = "人员类型 1 常驻人口 2 流动人员")
    private Long personType;

    /** 流动人员或者常住人口ID */
    @Excel(name = "流动人员或者常住人口ID")
    private Long personId;

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
    @SensitiveNew(setterEncrypt = "setName", getterDncrypt = "getName", EncryptMethod = "nameEncrypt", notEncryptByResponse = true)
    private String name;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String certType;

    /** 身份证号 */
    @Excel(name = "身份证号")
    @SensitiveNew(setterEncrypt = "setCertNo", getterDncrypt = "getCertNo", neeDecrypt = true, neeEecrypt = true)
    private String certNo;

    /** 联系方式 */
    @Excel(name = "联系方式")
    @SensitiveNew(setterEncrypt = "setPhone", getterDncrypt = "getPhone", neeDecrypt = true, neeEecrypt = true)
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
}
