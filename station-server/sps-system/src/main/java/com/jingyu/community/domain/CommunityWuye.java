package com.jingyu.community.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jingyu.common.utils.encryption_decryption.SensitiveNew;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = false)
@Data
public class CommunityWuye extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 所属小区 */
    @Excel(name = "所属小区")
    private Long communityId;

    private Community community;

    /** 工作单位 */
    @Excel(name = "工作单位")
    private String company;

    /** 工作类型 */
    @Excel(name = "工作类型")
    private String workType;

    /** 人员姓名 */
    @Excel(name = "人员姓名")
    @SensitiveNew(setterEncrypt = "setName", getterDncrypt = "getName", EncryptMethod = "nameEncrypt", notEncryptByResponse = true)
    private String name;

    /** 身份证号 */
    @Excel(name = "身份证号")
    @SensitiveNew(setterEncrypt = "setCertNo", getterDncrypt = "getCertNo", neeDecrypt = true, neeEecrypt = true)
    private String certNo;

    /** 手机号 */
    @Excel(name = "手机号")
    @SensitiveNew(setterEncrypt = "setPhone", getterDncrypt = "getPhone", neeDecrypt = true, neeEecrypt = true)
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

    @Excel(name = "人脸图片地址")
    private String faceImgUrl;

}
