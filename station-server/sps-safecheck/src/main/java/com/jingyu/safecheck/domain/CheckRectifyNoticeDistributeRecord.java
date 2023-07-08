package com.jingyu.safecheck.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 整改通知书下发对象 check_rectify_notice_distribute_record
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
public class CheckRectifyNoticeDistributeRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 被检查单位 */
    @Excel(name = "被检查单位")
    private String chectedUnit;

    /** 场所 */
    private Long placeId;

    /** 检查日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkDate;

    /** 被检查单位负责人 */
    @Excel(name = "被检查单位负责人")
    private String checkedUnitDirector;

    /** 被检查人账号 */
    @Excel(name = "被检查人账号")
    private Long checkedUnitDirectorAcccoutNumber;

    /** 责任民警 */
    @Excel(name = "责任民警")
    private String checkPolice;

    /** 问题隐患 */
    @Excel(name = "问题隐患")
    private String checkSaftyDanger;

    /** 整改措施 */
    @Excel(name = "整改措施")
    private String rectifyMeasure;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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
    public void setCheckedUnitDirectorAcccoutNumber(Long checkedUnitDirectorAcccoutNumber) 
    {
        this.checkedUnitDirectorAcccoutNumber = checkedUnitDirectorAcccoutNumber;
    }

    public Long getCheckedUnitDirectorAcccoutNumber() 
    {
        return checkedUnitDirectorAcccoutNumber;
    }
    public void setCheckPolice(String checkPolice) 
    {
        this.checkPolice = checkPolice;
    }

    public String getCheckPolice() 
    {
        return checkPolice;
    }
    public void setCheckSaftyDanger(String checkSaftyDanger) 
    {
        this.checkSaftyDanger = checkSaftyDanger;
    }

    public String getCheckSaftyDanger() 
    {
        return checkSaftyDanger;
    }
    public void setRectifyMeasure(String rectifyMeasure) 
    {
        this.rectifyMeasure = rectifyMeasure;
    }

    public String getRectifyMeasure() 
    {
        return rectifyMeasure;
    }
    public void setFinishDate(Date finishDate) 
    {
        this.finishDate = finishDate;
    }

    public Date getFinishDate() 
    {
        return finishDate;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("chectedUnit", getChectedUnit())
            .append("placeId", getPlaceId())
            .append("checkDate", getCheckDate())
            .append("checkedUnitDirector", getCheckedUnitDirector())
            .append("checkedUnitDirectorAcccoutNumber", getCheckedUnitDirectorAcccoutNumber())
            .append("checkPolice", getCheckPolice())
            .append("checkSaftyDanger", getCheckSaftyDanger())
            .append("rectifyMeasure", getRectifyMeasure())
            .append("finishDate", getFinishDate())
            .append("status", getStatus())
            .toString();
    }
}
