package com.jingyu.common.validator;

import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.xss.Xss;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotSqlInjectValidator implements ConstraintValidator<Xss, String>
{
    private static final String SQL_PATTERN = "\\b(and|exec|insert|select|drop|grant|alter|delete|update|count|chr|mid|master|truncate|char|declare|or)\\b|(\\*|;|\\+|'|%)";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext)
    {
        if (StringUtils.isBlank(value))
        {
            return true;
        }
        return !sqlInjectCheck(value);
    }

    public static boolean sqlInjectCheck(String sInput) {
        if (StringUtils.isEmpty(sInput))
            return true;
        Pattern pattern = Pattern.compile(SQL_PATTERN);
        Matcher matcher = pattern.matcher(sInput);
        return matcher.find();
    }
}
