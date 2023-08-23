package com.jingyu.safecheck.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 整改结果审核对象 check_rectify_result_review_record
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
public class CheckRectifyResultReviewRecord extends BaseEntity
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

    /** 被检查人账号 */
    @Excel(name = "被检查人账号")
    private Long checkedUnitDirectorAcccoutNumber;

    /** 责任民警 */
    @Excel(name = "责任民警")
    private String checkPolice;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

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

    /** 整改通知书签字照片 */
    @Excel(name = "整改通知书签字照片")
    private String rectifyNoticeSigned;

    /** 整改结果现场照片 */
    @Excel(name = "整改结果现场照片")
    private String rectifyResultSitePhoto;

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Excel(name = "被检查人电话号码")
    private Long phoneNumber;

    /** 审核状态（0：待审核1：审核通过2：审核不通过） */
    @Excel(name = "审核状态", readConverterExp = "0=：待审核1：审核通过2：审核不通过")
    private String reviewStatus;

    public String getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(String finishStatus) {
        this.finishStatus = finishStatus;
    }

    @Excel(name = "完成状态(0:未下发1：已下发2：已提交3：未提交）")
    private String finishStatus;

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
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
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
    public void setrectifyNoticeSigned(String rectifyNoticeSigned)
    {
        this.rectifyNoticeSigned = rectifyNoticeSigned;
    }

    public String getrectifyNoticeSigned()
    {
        return rectifyNoticeSigned;
    }
    public void setRectifyResultSitePhoto(String rectifyResultSitePhoto)
    {
        this.rectifyResultSitePhoto = rectifyResultSitePhoto;
    }

    public String getRectifyResultSitePhoto()
    {
        return rectifyResultSitePhoto;
    }
    public void setReviewStatus(String reviewStatus)
    {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewStatus()
    {
        return reviewStatus;
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
                .append("userId", getUserId())
                .append("deptId", getDeptId())
                .append("checkSaftyDanger", getCheckSaftyDanger())
                .append("rectifyMeasure", getRectifyMeasure())
                .append("finishDate", getFinishDate())
                .append("rectifyNoticeSigned", getrectifyNoticeSigned())
                .append("rectifyResultSitePhoto", getRectifyResultSitePhoto())
                .append("reviewStatus", getReviewStatus())
                .append("remark", getRemark())
                .append("finishStatus", getFinishStatus())
                .append("phoneNumber", getPhoneNumber())
                .toString();
    }
}