package com.jingyu.qunfangqunzhi.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 上报事件管理对象 event_info
 *
 * @author jingyu
 * @date 2023-07-03
 */
public class EventInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 事件id */
    private Long id;

    /** 提交用户id */
    @Excel(name = "提交用户id")
    private Long uploadUserId;

    /** 事件类别 */
    @Excel(name = "事件类别")
    private String eventType;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactPersonName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 发生地址 */
    @Excel(name = "发生地址")
    private String address;

    /** 发生地址经纬度信息 */
    @Excel(name = "发生地址经纬度信息")
    private String addressData;

    /** 事件描述 */
    @Excel(name = "事件描述")
    private String eventDescription;

    /** 发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发生时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date occurTime;

    /** 照片地址 */
    @Excel(name = "照片地址")
    private String photoUrl;

    /** 视频地址 */
    @Excel(name = "视频地址")
    private String videoUrl;

    /** 处理状态 0未确认，1已确认，2已完成 */
    @Excel(name = "处理状态 0未确认，1已确认，2已完成")
    private String status;

    /** 完成时提供反馈信息 */
    @Excel(name = "完成时提供反馈信息")
    private String reply;

    /** 事件上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "事件上传时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;


    /**
     * 事件概述
     */
    private String eventSummarize;


    //备注
    private String remark;


    /** $table.subTable.functionName信息 */
    private List<EventUserAllocated> eventUserAllocatedList;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUploadUserId(Long uploadUserId)
    {
        this.uploadUserId = uploadUserId;
    }

    public Long getUploadUserId()
    {
        return uploadUserId;
    }
    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public String getEventType()
    {
        return eventType;
    }
    public void setContactPersonName(String contactPersonName)
    {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonName()
    {
        return contactPersonName;
    }
    public void setContactPhone(String contactPhone)
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone()
    {
        return contactPhone;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setAddressData(String addressData)
    {
        this.addressData = addressData;
    }

    public String getAddressData()
    {
        return addressData;
    }
    public void setEventDescription(String eventDescription)
    {
        this.eventDescription = eventDescription;
    }

    public String getEventDescription()
    {
        return eventDescription;
    }
    public void setOccurTime(Date occurTime)
    {
        this.occurTime = occurTime;
    }

    public Date getOccurTime()
    {
        return occurTime;
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
    public void setReply(String reply)
    {
        this.reply = reply;
    }

    public String getReply()
    {
        return reply;
    }
    public void setUploadTime(Date uploadTime)
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime()
    {
        return uploadTime;
    }

    public List<EventUserAllocated> getEventUserAllocatedList()
    {
        return eventUserAllocatedList;
    }

    public void setEventUserAllocatedList(List<EventUserAllocated> eventUserAllocatedList)
    {
        this.eventUserAllocatedList = eventUserAllocatedList;
    }

    public String getEventSummarize() {
        return eventSummarize;
    }

    public void setEventSummarize(String eventSummarize) {
        this.eventSummarize = eventSummarize;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uploadUserId", getUploadUserId())
            .append("eventType", getEventType())
            .append("contactPersonName", getContactPersonName())
            .append("contactPhone", getContactPhone())
            .append("address", getAddress())
            .append("addressData", getAddressData())
            .append("eventDescription", getEventDescription())
            .append("occurTime", getOccurTime())
            .append("photoUrl", getPhotoUrl())
            .append("videoUrl", getVideoUrl())
            .append("status", getStatus())
            .append("reply", getReply())
            .append("uploadTime", getUploadTime())
            .append("eventUserAllocatedList", getEventUserAllocatedList())
            .toString();
    }
}
