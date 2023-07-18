package com.jingyu.safecheck.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 安全隐患检查场所字典对象 check_place_dict
 * 
 * @author ruoyi
 * @date 2023-06-28
 */
public class CheckPlaceDict extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 场所行业名称 */
    @Excel(name = "场所行业名称")
//    private String placeName;
    private Long placeName;

    /** 检查项列表 */
    @Excel(name = "检查项列表")
    private String checkItems;

    public String getRectifyNoticeTemplateParams() {
        return rectifyNoticeTemplateParams;
    }

    public void setRectifyNoticeTemplateParams(String rectifyNoticeTemplateParams) {
        this.rectifyNoticeTemplateParams = rectifyNoticeTemplateParams;
    }

    /** 整改通知书模板参数 */
    @Excel(name = "整改通知书模板参数")
    private String rectifyNoticeTemplateParams;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setPlaceName(Long placeName)
    {
        this.placeName = placeName;
    }

    public Long getPlaceName()
    {
        return placeName;
    }

//    public void setPlaceName(String placeName)
//    {
//        this.placeName = placeName;
//    }
//
//    public String getPlaceName()
//    {
//        return placeName;
//    }
    public void setCheckItems(String checkItems)
    {
        this.checkItems = checkItems;
    }

    public String getCheckItems()
    {
        return checkItems;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("placeName", getPlaceName())
            .append("checkItems", getCheckItems())
            .append("rectifyNoticeTemplateParams", getRectifyNoticeTemplateParams())
            .toString();
    }
}
