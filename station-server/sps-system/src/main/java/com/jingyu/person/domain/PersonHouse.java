package com.jingyu.person.domain;

import lombok.Data;

@Data
public class PersonHouse {

    /* 所属小区id */
    private Long communityId;
    /* 所属小区编码 */
    private Long communityCode;
    /* 地址主键id */
    private Long addressId;
    /* 人员报表主键id */
    private Long personId;
    /* 人员类型 常驻、流动 */
    private String personType;
    /* 是否为常驻地址 */
    private int livingFlag;
    /* 人员与房东关系 */
    private String relationshipWithOwner;
    /* 入住时间 */
    private String startTime;

}
