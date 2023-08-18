package com.jingyu.contradiction.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 纠纷调解记录对象 c_mediation_records
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MediationRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生成主键ID */
    private Long id;

    /** 案件编码 */
    @Excel(name = "案件编码")
    private String caseCode;

    /** 调解时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "调解时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date mediateTime;

    /** 参与纠纷人员 */
    @Excel(name = "参与纠纷人员")
    private String participantsInDisputes;

    /** 参与纠纷人员姓名 */
    private String participantsInDisputesName;

    /** 参与调解警员 */
    @Excel(name = "参与调解警员")
    private String participatingPolice;

    /** 调解方式 线上/线下 */
    @Excel(name = "调解方式 线上/线下")
    private String mediationMode;

    /** 调解地点 */
    @Excel(name = "调解地点")
    private String mediationLocation;

    /** 调解结果 */
    @Excel(name = "调解结果")
    private String mediationResults;

    /** 调解会议描述 */
    @Excel(name = "调解会议描述")
    private String mediationMeetingDescription;

    /** 操作类型0新增1修改 */
    private String operateType;

    /** 操作人 */
    private String operateName;

    /** 操作时间 */
    private Date operateTime;

    /** 文件名称 */
    private String fileName;

    /** 文件路径 */
    private String filePath;

    /** 文件主键ID */
    private Long fileId;
}
