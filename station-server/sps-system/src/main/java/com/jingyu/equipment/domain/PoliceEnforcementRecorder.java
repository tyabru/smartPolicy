package com.jingyu.equipment.domain;

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
 * 执法仪记录仪对象 police_enforcement_recorder
 * 
 * @author jiatongbo
 * @date 2023-07-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceEnforcementRecorder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生成主键ID */
    private Long id;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceCode;

    /** 制造商 */
    @Excel(name = "制造商")
    private String manufacturer;

    /** 型号 */
    @Excel(name = "型号")
    private String model;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 是否有夜视功能 */
    @Excel(name = "是否有夜视功能")
    private String nightVision;

    /** 图像分辨率 */
    @Excel(name = "图像分辨率")
    private String imageResolution;

    /** 录像分辨率 */
    @Excel(name = "录像分辨率")
    private String recordingResolution;

    /** 内存（16GB/32G/64G/128G/256G） */
    @Excel(name = "内存", readConverterExp = "16GB/32G/64G/128G/256G")
    private Long memory;

    /** 最大工作时长 */
    @Excel(name = "最大工作时长")
    private String maxWorkingHours;

    /** 网络通讯0协议1（3G/4G）2wifi3蓝牙 */
    @Excel(name = "网络通讯0协议1", readConverterExp = "3=G/4G")
    private Long networking;

    /** 电池容量 */
    @Excel(name = "电池容量")
    private String batteryCapacity;

    /** 状态：0在线，1离线 */
    @Excel(name = "状态：0在线，1离线")
    private Long statue;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deleteTime;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operateTime;

    /** 操作类型0 新增 1 更新 2 删除 */
    @Excel(name = "操作类型0 新增 1 更新 2 删除")
    private Long operateType;

    /** 操作人姓名 */
    @Excel(name = "操作人姓名")
    private String operateName;
}
