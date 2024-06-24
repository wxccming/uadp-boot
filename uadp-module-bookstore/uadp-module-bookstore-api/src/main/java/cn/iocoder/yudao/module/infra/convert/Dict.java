package cn.iocoder.yudao.module.infra.convert;

import java.lang.annotation.*;
 
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dict {
 
    /**
     * 字典类型名称
     */
    String dictTypeName() default "";
 
    /**
     * 字典存放后缀
     * 默认 "Text"
     * 例 原始字段名：type  翻译储存的字段名：typeText
     **/
    String suffix() default "";
 
}