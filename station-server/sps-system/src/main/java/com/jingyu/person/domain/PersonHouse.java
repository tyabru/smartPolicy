package com.jingyu.person.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * house对象 sq_person_house
 * 
 * @author jingyu
 * @date 2023-07-28
 */
@Data
public class PersonHouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 地址id */
    @Excel(name = "地址id")
    private String metaAddrId;

    private String shortName;
    private String fullAddress;

    /** 社区id */
    @Excel(name = "社区id")
    private Long communityId;

    /** $column.columnComment */
    @Excel(name = "社区编码")
    private String communityCode;

    private String communityName;

    /** 人员id */
    @Excel(name = "人员id")
    private Long personId;

    /** 人员类型 0 其他 1 常驻 2 流动 3 重点 */
    @Excel(name = "人员类型")
    private Long personType;

    /** 人员与房主的关系 本人、家庭成员、租户、暂住 */
    @Excel(name = "人员与房主的关系 本人、家庭成员、租户、暂住")
    private Integer relationshipWithOwner;

    /** 入住时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入住时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    @Excel(name = "是否重点人员")
    private String isImportant;
    @Excel(name = "关注级别")
    private String attentionLevel;

    private int delFlag;


}
