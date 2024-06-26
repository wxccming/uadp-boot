package cn.iocoder.yudao.module.infra.convert;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface User {
 
    /**
     * 类型名称 user_id    dept_id
     */
    String type() default "user_id";

}