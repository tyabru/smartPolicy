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
 * 矛盾纠纷基本信息对象 c_contradiction_tangles
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContradictionTangles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生产主键ID */
    private Long id;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 案件编码 */
    @Excel(name = "案件编码")
    private String caseCode;

    /** 纠纷标题/内容 */
    @Excel(name = "纠纷标题/内容")
    private String title;

    /** 纠纷类型 */
    @Excel(name = "纠纷类型")
    private String disputeType;

    /** 纠纷级别 */
    @Excel(name = "纠纷级别")
    private String disputeLevel;

    /** 发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发生时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 纠纷发生地点 */
    @Excel(name = "纠纷发生地点")
    private String place;

    /** 经度 */
    private String longitude;

    /** 纬度 */
    private String latitude;

    /** 报警方式 (入户调查发现、巡逻发现、群众反映、自述、其他) */
    @Excel(name = "报警方式 (入户调查发现、巡逻发现、群众反映、自述、其他)")
    private String alarmMode;

    /** 参与人数 */
    @Excel(name = "参与人数")
    private String partakeNum;

    /** 调解进度 */
    @Excel(name = "调解进度")
    private String mediationProgress;

    /** 是否成功调解 */
    @Excel(name = "是否成功调解")
    private String isMediateSuccessful;

    /** 调解最终结果描述 */
    @Excel(name = "调解最终结果描述")
    private String mediateResultDescription;

    /** 调节警员警号 */
    @Excel(name = "调节警员警号")
    private String policeNum;

    /** 是否转办 */
    @Excel(name = "是否转办")
    private String isTransfer;

    /** 转办人 */
    @Excel(name = "转办人")
    private String transferPoliceNum;

    /** 转办时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转办时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transferTime;

    /** 数据来源平台（群众自主、警员录入、网页、三方） */
    private String sourcePlatform;

    /** 操作类型0新增1修改2删除 */
    private String operateType;

    /** 操作人 */
    private String operateName;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发生时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /** 文件名称 */
    private String fileName;

    /** 文件路径 */
    private String filePath;

    /** 文件主键ID */
    private Long fileId;
}
