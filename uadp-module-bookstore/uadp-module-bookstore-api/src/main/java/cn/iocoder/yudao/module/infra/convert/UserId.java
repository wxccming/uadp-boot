package cn.iocoder.yudao.module.infra.convert;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserId {
 
    /**
     * 字典类型名称
     */
    String userId() default "";

}