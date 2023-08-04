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
 * 车载设备对象 police_onboard_equipment
 * 
 * @author jiatongbo
 * @date 2023-07-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceOnboardEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生成主键ID */
    private Long id;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 编码 */
    @Excel(name = "编码")
    private String deviceCode;

    /** 型号 */
    @Excel(name = "型号")
    private String model;

    /** 生产厂家 */
    @Excel(name = "生产厂家")
    private String manufacturer;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

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

    /** 部门名称 */
    private String deptName;
}
