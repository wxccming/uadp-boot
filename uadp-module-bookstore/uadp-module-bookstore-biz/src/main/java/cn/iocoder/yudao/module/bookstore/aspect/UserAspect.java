package cn.iocoder.yudao.module.bookstore.aspect;

import cn.iocoder.yudao.module.bookstore.config.UserInfoConvertConfiguration;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserAspect {
 
    @Pointcut("@within(cn.iocoder.yudao.module.infra.convert.UserCovert)")
    public void userCovert() {
    }
 
    @Around("userCovert()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.执行原方法
        Object proceed = joinPoint.proceed();
        //2.拿到原方法的原返回值 调用parseResult进行字典转换
        Object result = UserInfoConvertConfiguration.parseResult(proceed);
        UserInfoConvertConfiguration.clearCache();
        return result;
    }
}