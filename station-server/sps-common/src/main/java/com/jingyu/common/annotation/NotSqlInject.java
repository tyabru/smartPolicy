package com.jingyu.common.annotation;

import com.jingyu.common.validator.NotSqlInjectValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = {NotSqlInjectValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
public @interface NotSqlInject {
}
