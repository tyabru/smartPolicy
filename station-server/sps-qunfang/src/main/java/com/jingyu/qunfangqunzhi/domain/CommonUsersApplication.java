package com.jingyu.qunfangqunzhi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 申请管理对象 common_users_application
 *
 * @author jingyu
 * @date 2023-07-05
 */
public class CommonUsersApplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主id */
    private Long id;

    /** 普通用户id */
    @Excel(name = "普通用户id")
    private Long userId;

    /** 说明 */
    @Excel(name = "说明")
    private String description;

    /** 申请类型 */
    @Excel(name = "申请类型")
    private String applicationType;

    /** 处理状态，0未处理，1已接受，2已拒绝 */
    @Excel(name = "处理状态，0未处理，1已接受，2已拒绝")
    private String status;

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
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setApplicationType(String applicationType)
    {
        this.applicationType = applicationType;
    }

    public String getApplicationType()
    {
        return applicationType;
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
            .append("userId", getUserId())
            .append("description", getDescription())
            .append("applicationType", getApplicationType())
            .append("status", getStatus())
            .toString();
    }
}
