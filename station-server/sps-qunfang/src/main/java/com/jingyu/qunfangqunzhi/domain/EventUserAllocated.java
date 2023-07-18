package com.jingyu.qunfangqunzhi.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * ${subTable.functionName}对象 event_user_allocated
 *
 * @author jingyu
 * @date 2023-07-03
 */
public class EventUserAllocated extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主id */
    private Long id;

    /** 分配userid */
    @Excel(name = "分配userid")
    private Long userId;

    /** 事件id */
    @Excel(name = "事件id")
    private Long eventId;

    /** 检查情况 */
    @Excel(name = "检查情况")
    private String inspectDetail;

    /** 整改意见 */
    @Excel(name = "整改意见")
    private String rectifyOpinion;

    /** 整改结果 */
    @Excel(name = "整改结果")
    private String rectifyResult;

    /** 检查位置 */
    @Excel(name = "检查位置")
    private String inspectLocation;

    /** 检查日期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "检查日期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectDatetime;

    /** 存在问题 */
    @Excel(name = "存在问题")
    private String existProblem;

    /** 照片地址 */
    @Excel(name = "照片地址")
    private String photoUrl;

    /** 视频地址 */
    @Excel(name = "视频地址")
    private String videoUrl;

    /** 事件处理状态，0未确认，1已确认，2已取消，3已完成 */
    @Excel(name = "事件处理状态，0未确认，1已确认，2已取消，3已完成")
    private String status;

    /** 分配任务 */
    @Excel(name = "分配任务")
    private String allocatedUserType;

    /** 分配任务用户的id */
    @Excel(name = "分配任务用户的id")
    private Long allocateUserId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setEventId(Long eventId)
    {
        this.eventId = eventId;
    }

    public Long getEventId()
    {
        return eventId;
    }
    public void setInspectDetail(String inspectDetail)
    {
        this.inspectDetail = inspectDetail;
    }

    public String getInspectDetail()
    {
        return inspectDetail;
    }
    public void setRectifyOpinion(String rectifyOpinion)
    {
        this.rectifyOpinion = rectifyOpinion;
    }

    public String getRectifyOpinion()
    {
        return rectifyOpinion;
    }
    public void setRectifyResult(String rectifyResult)
    {
        this.rectifyResult = rectifyResult;
    }

    public String getRectifyResult()
    {
        return rectifyResult;
    }
    public void setInspectLocation(String inspectLocation)
    {
        this.inspectLocation = inspectLocation;
    }

    public String getInspectLocation()
    {
        return inspectLocation;
    }
    public void setInspectDatetime(Date inspectDatetime)
    {
        this.inspectDatetime = inspectDatetime;
    }

    public Date getInspectDatetime()
    {
        return inspectDatetime;
    }
    public void setExistProblem(String existProblem)
    {
        this.existProblem = existProblem;
    }

    public String getExistProblem()
    {
        return existProblem;
    }
    public void setPhotoUrl(String photoUrl)
    {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl()
    {
        return photoUrl;
    }
    public void setVideoUrl(String videoUrl)
    {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl()
    {
        return videoUrl;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setAllocatedUserType(String allocatedUserType)
    {
        this.allocatedUserType = allocatedUserType;
    }

    public String getAllocatedUserType()
    {
        return allocatedUserType;
    }
    public void setAllocateUserId(Long allocateUserId)
    {
        this.allocateUserId = allocateUserId;
    }

    public Long getAllocateUserId()
    {
        return allocateUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("eventId", getEventId())
            .append("inspectDetail", getInspectDetail())
            .append("rectifyOpinion", getRectifyOpinion())
            .append("rectifyResult", getRectifyResult())
            .append("inspectLocation", getInspectLocation())
            .append("inspectDatetime", getInspectDatetime())
            .append("existProblem", getExistProblem())
            .append("photoUrl", getPhotoUrl())
            .append("videoUrl", getVideoUrl())
            .append("status", getStatus())
            .append("allocatedUserType", getAllocatedUserType())
            .append("allocateUserId", getAllocateUserId())
            .toString();
    }
}
