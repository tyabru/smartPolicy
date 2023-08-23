package com.jingyu.system.domain.vo;

import com.jingyu.common.annotation.NotSqlInject;
import com.jingyu.common.xss.Xss;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DictExistsVo {

    @Xss
    @NotSqlInject
    @NotEmpty(message = "字典类型不能为空")
    private String dictType;
    @Xss
    @NotSqlInject
    private String dictValue;
    @Xss
    @NotSqlInject
    private String dictLabel;
    @Xss
    @NotSqlInject
    private String dictName;
}
