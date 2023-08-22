package com.jingyu.safecheck.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 安全隐患检查登记对象 check_danger_record
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
public class CheckDangerRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 被检查单位 */
    @Excel(name = "被检查单位")
    private String chectedUnit;

    /** 场所 */
    @Excel(name = "场所")
    private Long placeId;

    /** 检查日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkDate;

    /** 被检查单位负责人 */
    @Excel(name = "被检查单位负责人")
    private String checkedUnitDirector;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 检查人员 */
    @Excel(name = "检查人员")
    private String checkPerson;

    /** 检查项填写结果 */
    @Excel(name = "检查项填写结果")
    private String checkItemResults;

    /** 其他安全隐患 */
    @Excel(name = "其他安全隐患")
    private String otherSaftyDanger;

    /** 检查记录表签名文件 */
    @Excel(name = "检查记录表签名文件")
    private String checkResult;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setChectedUnit(String chectedUnit)
    {
        this.chectedUnit = chectedUnit;
    }

    public String getChectedUnit()
    {
        return chectedUnit;
    }
    public void setPlaceId(Long placeId)
    {
        this.placeId = placeId;
    }

    public Long getPlaceId()
    {
        return placeId;
    }
    public void setCheckDate(Date checkDate)
    {
        this.checkDate = checkDate;
    }

    public Date getCheckDate()
    {
        return checkDate;
    }
    public void setCheckedUnitDirector(String checkedUnitDirector)
    {
        this.checkedUnitDirector = checkedUnitDirector;
    }

    public String getCheckedUnitDirector()
    {
        return checkedUnitDirector;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setCheckPerson(String checkPerson)
    {
        this.checkPerson = checkPerson;
    }

    public String getCheckPerson()
    {
        return checkPerson;
    }
    public void setCheckItemResults(String checkItemResults)
    {
        this.checkItemResults = checkItemResults;
    }

    public String getCheckItemResults()
    {
        return checkItemResults;
    }
    public void setOtherSaftyDanger(String otherSaftyDanger)
    {
        this.otherSaftyDanger = otherSaftyDanger;
    }

    public String getOtherSaftyDanger()
    {
        return otherSaftyDanger;
    }
    public void setCheckResult(String checkResult)
    {
        this.checkResult = checkResult;
    }

    public String getCheckResult()
    {
        return checkResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("chectedUnit", getChectedUnit())
                .append("placeId", getPlaceId())
                .append("checkDate", getCheckDate())
                .append("checkedUnitDirector", getCheckedUnitDirector())
                .append("deptId", getDeptId())
                .append("userId", getUserId())
                .append("checkPerson", getCheckPerson())
                .append("checkItemResults", getCheckItemResults())
                .append("otherSaftyDanger", getOtherSaftyDanger())
                .append("checkResult", getCheckResult())
                .toString();
    }
}