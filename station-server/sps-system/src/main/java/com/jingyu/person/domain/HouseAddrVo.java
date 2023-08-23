package com.jingyu.person.domain;

import lombok.Data;

@Data
public class HouseAddrVo {

    private String communityId;
    private String communityName;
    private String communityCode;

    private String metaAddrId;
    private String fullAddress;
    private String shortName;

    private String isImportant;

    private int residents;
    private int flows;
    private int importants;

}
