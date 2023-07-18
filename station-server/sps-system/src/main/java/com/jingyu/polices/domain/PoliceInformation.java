package com.jingyu.polices.domain;

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
 * 警员基本信息对象 police_information
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生成编码 */
    private Long id;

    /** 警号 */
    @Excel(name = "警号")
    private String policeNumber;

    /** 姓名 */
    @Excel(name = "姓名")
    @SensitiveNew(setterEncrypt = "setPoliceName", getterDncrypt = "getPoliceName", EncryptMethod = "nameEncrypt", notEncryptByResponse = true)
    private String policeName;

    /** 性别0女1男 */
    @Excel(name = "性别0女1男")
    private Long gender;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long policeAge;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 出生日期 */
    @Excel(name = "出生日期")
    private String birthday;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    @SensitiveNew(setterEncrypt = "setIdCard", getterDncrypt = "getIdCard", neeDecrypt = true, neeEecrypt = true)
    private String idCard;

    /** 手机号 */
    @Excel(name = "手机号")
    @SensitiveNew(setterEncrypt = "setPhoneNumber", getterDncrypt = "getPhoneNumber", neeDecrypt = true, neeEecrypt = true)
    private String phoneNumber;

    /** 警员邮箱 */
    @Excel(name = "警员邮箱")
    private String email;

    /** 政治面貌0党员1团员2群众 */
    @Excel(name = "政治面貌0党员1团员2群众")
    private Long identityType;

    /** 学历 */
    @Excel(name = "学历")
    private Long education;

    /** 毕业院校 */
    @Excel(name = "毕业院校")
    private String graduationSchool;

    /** 警察类型0民警1辅警 */
    @Excel(name = "警察类型0民警1辅警")
    private Long policePost;

    /** 所属岗位 */
    @Excel(name = "所属岗位")
    private Long policeType;

    /** 技能专长 */
    @Excel(name = "技能专长")
    private String speciality;

    /** 所属派出所ID */
    @Excel(name = "所属派出所ID")
    private String deptId;

    /** 警员照片 */
    @Excel(name = "警员照片")
    private String policePhoto;

    /** 小区编码 */
    @Excel(name = "小区编码")
    private String communityCode;

    /** 居住地址编码 */
    @Excel(name = "居住地址编码")
    private String addressCode;

    /** 入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date deleteTime;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /** 操作类型0新增1修改2删除3封存 */
    @Excel(name = "操作类型0新增1修改2删除3封存")
    private Long operateType;

    /** 操作人姓名 */
    @Excel(name = "操作人姓名")
    private String operateName;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long UserId;

}
