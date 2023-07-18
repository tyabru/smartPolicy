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
 * 警用设备管理对象 police_equipment_claim_return
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceEquipmentClaimReturn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生成编码 */
    private Long id;

    /** 警号 */
    @Excel(name = "警号")
    private String policeNumber;

    /** 装备编码 */
    @Excel(name = "装备编码")
    private String equipmentNumber;

    /** 配发日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "配发日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date distributionDate;

    /** 配发原因 */
    @Excel(name = "配发原因")
    private String receivingReason;

    /** 是否归还 */
    @Excel(name = "是否归还")
    private String isReturn;

    /** 归还日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "归还日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date returnDate;

    /** 装备是否损坏(0是1否) */
    @Excel(name = "装备是否损坏(0是1否)")
    private String isEquipmentDefective;

    /** 损坏原因 */
    @Excel(name = "损坏原因")
    private String causesOfDamage;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Integer deptId;

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

    /** 操作类型0新增1修改3删除 */
    @Excel(name = "操作类型0新增1修改3删除")
    private Long operateType;

    /** 操作人姓名 */
    @Excel(name = "操作人姓名")
    private String operateName;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;
}
