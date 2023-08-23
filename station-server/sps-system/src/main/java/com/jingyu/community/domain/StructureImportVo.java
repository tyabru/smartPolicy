package com.jingyu.community.domain;

import com.jingyu.common.annotation.Excel;
import com.jingyu.common.exception.CustomException;
import com.jingyu.common.exception.ExcelRowErrorInsertException;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.bean.BeanUtils;
import com.jingyu.common.utils.bean.BeanValidators;
import com.jingyu.common.xss.Xss;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jingyu.common.constant.Constants.ADDR_REGX;

@ToString
public class StructureImportVo {

    @Excel(name="小区名称")
    @Xss
    @NotEmpty(message = "小区名称不能为空！")
    private String communityName;
    @Excel(name="小区编码")
    @Xss
    @NotEmpty(message = "小区编码不能为空！")
    private String communityCode;
    @Excel(name = "地址全称")
    @Xss
    @NotEmpty(message = "地址全称不能为空！")
    private String fullAddress;
    @Excel(name = "地址编码")
    @Xss
    @NotEmpty(message = "地址编码不能为空！")
    private String metaAddrId;

    /** 所属区域 */
    @Excel(name = "所属区域")
    @Xss
    private String region;

    /** 区域名称 */
    @Excel(name = "区域名称")
    @Xss
    private String regionName;
    @Excel(name = "经度")
    private double longitude;
    @Excel(name = "纬度")
    private double latitude;

    private Long metaLevel = 10L;
    private String shortName;
    private String dong;
    private String unit;
    private String ceng;
    private String room;

    public String getCommunityName() {
        return communityName;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getMetaAddrId() {
        return metaAddrId;
    }

    public String getRegion() {
        return region;
    }

    public String getRegionName() {
        return regionName;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Long getMetaLevel() {
        return metaLevel;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDong() {
        return dong;
    }

    public String getUnit() {
        return unit;
    }

    public String getCeng() {
        return ceng;
    }

    public String getRoom() {
        return room;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public void setMetaAddrId(String metaAddrId) {
        this.metaAddrId = metaAddrId;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setMetaLevel(Long metaLevel) {
        this.metaLevel = metaLevel;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCeng(String ceng) {
        this.ceng = ceng;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setFullAddress(String fullAddress) {
        if(StringUtils.isNotEmpty(fullAddress)) {
            Matcher matcher = ADDR_REGX.matcher(fullAddress);
            while (matcher.find()) {
                if(StringUtils.isEmpty(matcher.group())) {
                    break;
                }
                int i = matcher.groupCount();
                setShortName(matcher.group(0));
                if(i == 4) {
                    setDong(matcher.group(1));
                    setUnit(matcher.group(2));
                    setCeng(matcher.group(3));
                    setRoom(matcher.group(4));
                    if(StringUtils.isNotEmpty(matcher.group(4))) {
                        setMetaLevel(14L);
                    } else if(StringUtils.isNotEmpty(matcher.group(3))) {
                        setMetaLevel(13L);
                    } else if(StringUtils.isNotEmpty(matcher.group(2))) {
                        setMetaLevel(12L);
                    } else if(StringUtils.isNotEmpty(matcher.group(1))) {
                        setMetaLevel(11L);
                    } else {
                        setMetaLevel(10L);
                    }
                } else {
                    throw new CustomException("无法匹配地址： " + fullAddress);
                }
            }
        }
        this.fullAddress = fullAddress;
    }

    public CommunityStructure parseToDo(int index) {
        // 创建校验器
        Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        // 执行校验
        Set<ConstraintViolation<StructureImportVo>> violations = validator.validate(this);
        // 处理校验结果
        if (!violations.isEmpty()) {
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<StructureImportVo> violation : violations) {
                message.append(violation.getMessage()).append(";");
            }
            throw new ExcelRowErrorInsertException(index, message.toString());
        } else {
            if(getMetaLevel() < 10) {
                return null;
            }
            CommunityStructure structure = new CommunityStructure();
            BeanUtils.copyBeanProp(structure, this);
            return structure;
        }

    }

}
