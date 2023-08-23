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
 * 警员分组详情对象 police_duty_groups
 * 
 * @author jiatongbo
 * @date 2023-07-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceDutyGroups extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生成主键ID */
    private Long id;

    /** 名 */
    @Excel(name = "名")
    private String groupName;

    /** 组员 */
    @Excel(name = "组员")
    private String teamMembers;

    /** 负责区域 */
    @Excel(name = "负责区域")
    private String responsibleArea;

    /** 值班类型0社区警务1执法办案 */
    @Excel(name = "值班类型0社区警务1执法办案")
    private String dutyType;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deleteTime;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operateTime;

    /** 操作类型0新增1修改2删除 */
    @Excel(name = "操作类型0新增1修改2删除")
    private Long operateType;

    /** 操作人姓名 */
    @Excel(name = "操作人姓名")
    private String operateName;

    /** 警员姓名 */
    private String policeNames;
}
