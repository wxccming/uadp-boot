package cn.iocoder.yudao.module.infra.convert;

import java.lang.annotation.*;
 
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictCovert {
 
}