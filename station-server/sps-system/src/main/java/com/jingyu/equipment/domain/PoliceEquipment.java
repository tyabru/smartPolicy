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
 * 警用装备配发领取记录对象 police_equipment
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生成编号 */
    private Long id;

    /** 装备编码 */
    @Excel(name = "装备编码")
    private String equipmentNumber;

    /** 装备类型 */
    @Excel(name = "装备类型")
    private Long equipmentType;

    /** 装备描述 */
    @Excel(name = "装备描述")
    private String equipmentDescription;

    /** 采购时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采购时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date procurementTime;

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

    /** 操作人姓名 */
    @Excel(name = "操作人姓名")
    private String operateName;

    /** 操作类型0新增1修改2删除 */
    @Excel(name = "操作类型0新增1修改2删除")
    private Long operateType;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;
}
