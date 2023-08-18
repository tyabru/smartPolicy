package com.jingyu.contradiction.domain;

import java.util.Date;
import com.jingyu.common.utils.encryption_decryption.SensitiveNew;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 参与人员信息对象 c_disputing_personnels
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DisputingPersonnel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生产主键ID */
    private Long id;

    /** 矛盾纠纷ID */
    private Long disputeId;

    /** 案件编码 */
    private String caseCode;

    /** 人员姓名 */
    @Excel(name = "人员姓名")
    @SensitiveNew(setterEncrypt = "setPeopleName", getterDncrypt = "getPeopleName", EncryptMethod = "nameEncrypt", notEncryptByResponse = true)
    private String peopleName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    @SensitiveNew(setterEncrypt = "setPhone", getterDncrypt = "getPhone", neeDecrypt = true, neeEecrypt = true)
    private String phone;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    @SensitiveNew(setterEncrypt = "setCerNo", getterDncrypt = "getCerNo", neeDecrypt = true, neeEecrypt = true)
    private String cerNo;

    /** 居住地址 */
    @Excel(name = "居住地址")
    private String residentialAddress;

    /** 工作单位 */
    @Excel(name = "工作单位")
    private String workUnit;

    /** 工作单位地址 */
    @Excel(name = "工作单位地址")
    private String workAddress;

    /** 参与原因 */
    @Excel(name = "参与原因")
    private String partakePerson;

    /** 参与角色 */
    @Excel(name = "参与角色")
    private String partakeRole;

    /** 人员照片路径 */
    @Excel(name = "人员照片路径")
    private String imageUrl;

    /** 操作类型0新增1修改2删除 */
    private String operateType;

    /** 操作时间 */
    private Date operateTime;

    /** 操作人 */
    private String operateName;
}
