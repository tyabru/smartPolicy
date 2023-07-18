package com.jingyu.safecheck.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 安全隐患检查项字典对象 check_item_dict
 * 
 * @author ruoyi
 * @date 2023-06-29
 */
public class CheckItemDict extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 检查项文字描述 */
    @Excel(name = "检查项文字描述")
    private String checkItemDescribe;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCheckItemDescribe(String checkItemDescribe) 
    {
        this.checkItemDescribe = checkItemDescribe;
    }

    public String getCheckItemDescribe() 
    {
        return checkItemDescribe;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("checkItemDescribe", getCheckItemDescribe())
            .toString();
    }
}
