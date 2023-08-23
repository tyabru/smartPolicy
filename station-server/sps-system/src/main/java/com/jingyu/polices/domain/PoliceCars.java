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
 * 警用车辆对象 police_cars
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceCars extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生成编码 */
    private Long id;

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String licenseNumber;

    /** 公安内部车辆编码 */
    @Excel(name = "公安内部车辆编码")
    private String carCode;

    /** 车牌类型 */
    @Excel(name = "车牌类型")
    private String licenseType;

    /** 车辆类型 */
    @Excel(name = "车辆类型")
    private String carType;

    /** 车辆品牌 */
    @Excel(name = "车辆品牌")
    private String carBrand;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Integer deptId;

    /** 车辆长度,单位cm */
    @Excel(name = "车辆长度,单位cm")
    private Double carLong;

    /** 车辆宽度,单位cm */
    @Excel(name = "车辆宽度,单位cm")
    private Double carHeight;

    /** 车辆高度,单位cm */
    @Excel(name = "车辆高度,单位cm")
    private Double carWide;

    /** 车辆照片 */
    @Excel(name = "车辆照片")
    private String vehiclePhotos;

    /** 是否配备车载布控球 */
    @Excel(name = "是否配备车载布控球")
    private String isVehicle;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String equipmentNumber;

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

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /** 操作类型0 新增 1 更新 2 删除 */
    @Excel(name = "操作类型0 新增 1 更新 2 删除")
    private String operateType;

    /** 操作人姓名 */
    @Excel(name = "操作人姓名")
    private String operateName;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 部门名称 */
    private String deptName;
}
