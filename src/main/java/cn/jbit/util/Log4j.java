package cn.jbit.util;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class Log4j {
    private Logger log= Logger.getLogger(Log4j.class);

    @Pointcut("execution(* cn.jbit.biz..*.*(..))")//定义注解切入点
    public  void pointcut(){}

    //代表前至增强的方法
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        log.info("\nBefore**调用:"+joinPoint.getTarget()+"的"+joinPoint.getSignature().getName()
                +"方法.方法入参:"+ Arrays.toString(joinPoint.getArgs()));
    }
    //代表后至增强的方法
    @AfterReturning(pointcut = "pointcut()",returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        log.info("\nAfterReturning**调用:"+joinPoint.getTarget()+"的"+joinPoint.getSignature().getName()
        +"方法.返回值:"+ result);
        }

    //异常抛出增强
    @AfterThrowing(pointcut = "pointcut()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,RuntimeException e){
        log.error(joinPoint.getSignature().getName()+"方法发生异常："+e);
   }

}
