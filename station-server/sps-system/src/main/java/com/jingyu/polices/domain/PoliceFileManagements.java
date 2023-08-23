package com.jingyu.polices.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jingyu.common.annotation.Excel;
import com.jingyu.common.core.domain.BaseEntity;

/**
 * 日常文件管理对象 police_file_managements
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PoliceFileManagements extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统自动生成编码 */
    private Long id;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件存储路径 */
    @Excel(name = "文件存储路径")
    private String filePath;

    /** 存储的文件夹名称 */
    @Excel(name = "存储的文件夹名称")
    private String folderName;

    /** 文件描述 */
    @Excel(name = "文件描述")
    private String fileDescription;

    /** 部门ID */
    @Excel(name = "部门ID")
<<<<<<< HEAD
    private Long deptId;

    /** 文件类型0矛盾纠纷记录相关说明文件1矛盾纠纷调解记录文件2单位内部相关文件 */
    @Excel(name = "文件类型")
    private Long fileType;
=======
    private Integer deptId;
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date deleteTime;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /** 操作人姓名 */
    @Excel(name = "操作人姓名")
    private String operateName;

    /** 操作类型0新增1修改2删除 */
    @Excel(name = "操作类型0新增1修改2删除")
    private Long operateType;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 部门名称 */
    private String deptName;
}
