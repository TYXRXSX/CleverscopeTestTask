package ru.clevertec.cleverscope.aspects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class HttpLogger {

    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void logIncomingRequest(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

        log.info("Incoming Request: " + request.getMethod() + " " + request.getRequestURI() +
                ", Handler: " + joinPoint.getSignature().toShortString() +
                ", Request Mapping: " + requestMapping);
    }

    @AfterReturning(pointcut = "@within(org.springframework.web.bind.annotation.RestController)", returning = "result")
    public void logOutgoingResponse(JoinPoint joinPoint, Object result) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

        log.info("Outgoing Response: " + request.getMethod() + " " + request.getRequestURI() +
                ", Handler: " + joinPoint.getSignature().toShortString() +
                ", Request Mapping: " + requestMapping +
                ", Response: " + result);
    }

}
