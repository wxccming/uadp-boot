package cn.iocoder.yudao.module.bookstore.aspect;

import cn.iocoder.yudao.module.bookstore.config.DictConfiguration;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DictAspect {
 
//    @Pointcut("@annotation(cn.iocoder.yudao.module.infra.convert.DictCovert)")
    @Pointcut("@within(cn.iocoder.yudao.module.infra.convert.DictCovert)")
    public void dictCovert() {
    }
 
    @Around("dictCovert()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.执行原方法
        Object proceed = joinPoint.proceed();
        //2.拿到原方法的原返回值 调用parseResult进行字典转换
        Object result = DictConfiguration.parseResult(proceed);
        DictConfiguration.clearCache();
        return result;
    }
}