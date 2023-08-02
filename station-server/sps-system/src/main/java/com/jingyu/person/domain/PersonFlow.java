package com.jingyu.person.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 流动人口对象 sq_person_flow
 * 
 * @author jingyu
 * @date 2023-07-21
 */
@Data
public class PersonFlow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 编码 */
    private String bm;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 证件类型 */
    private String certType;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String certNo;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 籍贯 */
    private String nativePlace;

    /** 民族 */
    private String nation;

    /** 入住日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入住日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date livingDate;

    /** 人脸图片 */
    @Excel(name = "人脸图片")
    private String faceImgUrl;

    /** 特征值 */
    private String features;

    /** 公司 */
    @Excel(name = "公司")
    private String company;

    /** 职业 */
    @Excel(name = "职业")
    private String career;


    /** 是否是重点人员 */
    @Excel(name = "是否是重点人员")
    private String isImportant;

    /** 关注的级别 0绿 1黄 2红 */
    @Excel(name = "关注的级别 0绿 1黄 2红")
    private String attentionLevel;

    private List<PersonHouse> houseList = new ArrayList<>();

}
