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
 * 单位员工对象 sq_company_employee
 * 
 * @author jingyu
 * @date 2023-07-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    @SensitiveNew(setterEncrypt = "setName", getterDncrypt = "getName", EncryptMethod = "nameEncrypt", notEncryptByResponse = true)
    private String name;

    /** 联系方式 */
    @Excel(name = "联系方式")
    @SensitiveNew(setterEncrypt = "setPhone", getterDncrypt = "getPhone", neeDecrypt = true, neeEecrypt = true)
    private String phone;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String identityType;

    /** 证件编号 */
    @Excel(name = "证件编号")
    @SensitiveNew(setterEncrypt = "setIdentityCode", getterDncrypt = "getIdentityCode", neeDecrypt = true, neeEecrypt = true)
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

    /** 所属单位 */
    private String companyName;
}
