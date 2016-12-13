package com.courseplatform.aop;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author chen cy
 * Created by ye on 2016/12/13.
 */
@Component
@Aspect
public class LogAop {
    private static final Logger LOG = LoggerFactory.getLogger(LogAop.class);

    @Before("execution(* com.courseplatform.handler.*.*(..))")
    public void before(JoinPoint point) {
        System.out.println("point.toLongString() = " + point.toLongString());
        System.out.println("point.toShortString() = " + point.toShortString());
        System.out.println("point.getKind() = " + point.getKind());
        System.out.println("point.getClass() = " + point.getClass());
        System.out.println("point.getArgs() = " + point.getArgs());
        System.out.println("point.getSignature() = " + point.getSignature());
        System.out.println("point.getStaticPart() = " + point.getStaticPart());
        System.out.println("point.getStaticPart().toShortString() = " + point.getStaticPart().toShortString());
    }
}
