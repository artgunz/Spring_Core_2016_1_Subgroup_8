package spring.core.aop.aspect.impl;

import spring.core.aop.annotation.Loggable;
import spring.core.aop.annotation.LoggableAround;
import spring.core.aop.aspect.LoggerAspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class DefaultLoggerAspect implements LoggerAspect {

    @Around(value = "@annotation(loggableAround)")
    public Object aroundBookingServiceLoggableMethod(ProceedingJoinPoint proceedingJoinPoint, LoggableAround loggableAround) {
        final Logger LOGGER = LogManager.getLogger(proceedingJoinPoint.getTarget().getClass());

        String methodName = proceedingJoinPoint.getSignature().getName();

        LOGGER.info("Entering {} method...", methodName);

        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        LOGGER.info("Exiting {} method with result {}", methodName, result);

        return result;
    }

}
