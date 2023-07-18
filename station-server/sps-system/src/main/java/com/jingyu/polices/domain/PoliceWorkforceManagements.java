package com.jingyu.polices.domain;

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
 * 排班管理对象 police_workforce_managements
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceWorkforceManagements extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生成编码 */
    private Long id;

    /** 值班组ID */
    @Excel(name = "值班组ID")
    private Integer dutyGroupId;

    /** 警号 */
    @Excel(name = "警号")
    private String policeNumber;

    /** 值班警员姓名 */
    @Excel(name = "值班警员姓名")
    private String policeNames;

    /** 值班名称(例如：早班，中班，晚班) */
    @Excel(name = "值班名称(例如：早班，中班，晚班)")
    private String dutyType;

    /** 值班日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "值班日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dutyDate;

    /** 带班领导 */
    @Excel(name = "带班领导")
    private String shiftLeaderPoliceNumber;

    /** 值班情况 */
    @Excel(name = "值班情况")
    private String dutySituation;

    /** 交接事项 */
    @Excel(name = "交接事项")
    private String handoverMatters;

    /** 接班组ID */
    @Excel(name = "接班组ID")
    private Integer successorGroupId;

    /** 接班人警号 */
    @Excel(name = "接班人警号")
    private String successorPoliceNumber;

    /** 接班时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接班时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date successionTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date deleteTime;

    /** 操作人姓名 */
    @Excel(name = "操作人姓名")
    private String operateName;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /** 操作类型0新增1修改2删除 */
    @Excel(name = "操作类型0新增1修改2删除")
    private Long operateType;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;
}
