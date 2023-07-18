package com.jingyu.common.utils.encryption_decryption;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD})
@Inherited
public @interface SensitiveNew {

    String setterEncrypt();
    String getterDncrypt();
    String EncryptMethod() default "";
    boolean neeDecrypt() default  false;
    boolean neeEecrypt() default  false;
    /** 此字段仅在加密开关关闭的情况下生效 */
    boolean notEncryptByResponse() default false;
}
