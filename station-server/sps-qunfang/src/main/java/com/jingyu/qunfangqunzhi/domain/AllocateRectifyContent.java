package com.jingyu.qunfangqunzhi.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 下发任务修改对象 allocate_rectify_content
 *
 * @author jingyu
 * @date 2023-07-26
 */
public class AllocateRectifyContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 分配事件id */
    @Excel(name = "分配事件id")
    private Long allocateId;

    /** 下发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    /** 修改意见 */
    @Excel(name = "修改意见")
    private String rectifyOpinion;

    /** 是否被确认，0为否，1为是 */
    @Excel(name = "是否被确认，0为否，1为是")
    private String confirmed;

    /** 下发用户id */
    @Excel(name = "下发用户id")
    private Long sendUserId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAllocateId(Long allocateId)
    {
        this.allocateId = allocateId;
    }

    public Long getAllocateId()
    {
        return allocateId;
    }
    public void setSendTime(Date sendTime)
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime()
    {
        return sendTime;
    }
    public void setRectifyOpinion(String rectifyOpinion)
    {
        this.rectifyOpinion = rectifyOpinion;
    }

    public String getRectifyOpinion()
    {
        return rectifyOpinion;
    }
    public void setConfirmed(String confirmed)
    {
        this.confirmed = confirmed;
    }

    public String getConfirmed()
    {
        return confirmed;
    }
    public void setSendUserId(Long sendUserId)
    {
        this.sendUserId = sendUserId;
    }

    public Long getSendUserId()
    {
        return sendUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("allocateId", getAllocateId())
            .append("sendTime", getSendTime())
            .append("rectifyOpinion", getRectifyOpinion())
            .append("confirmed", getConfirmed())
            .append("sendUserId", getSendUserId())
            .toString();
    }
}
