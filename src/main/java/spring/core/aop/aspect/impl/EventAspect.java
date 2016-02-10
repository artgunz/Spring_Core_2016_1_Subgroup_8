package spring.core.aop.aspect.impl;

import spring.core.aop.AOPHelper;
import spring.core.aop.annotation.Countable;
import spring.core.aop.annotation.EventName;
import spring.core.aop.annotation.Loggable;
import spring.core.aop.aspect.CounterAspect;
import spring.core.aop.aspect.LoggerAspect;
import spring.core.aop.handler.CountableMethodHandler;
import spring.core.aop.handler.data.CountedEventMethodData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Aspect
public class EventAspect implements CounterAspect, LoggerAspect {

    private static final Logger LOGGER = LogManager.getLogger(EventAspect.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("execution(* spring.core.service.EventService.*(..))")
    public void inEventService() {
    }

    @Pointcut("execution(public * * (.., @spring.core.aop.annotation.EventName (String), ..))")
    public void methodWithAnnotationOnAtLeastOneParameter() {
    }

    @Before("inEventService() && @annotation(countable) && methodWithAnnotationOnAtLeastOneParameter()")
    public void beforeEventServiceCountableMethod(JoinPoint point, Countable countable) {
        String methodName = point.getSignature().getName();
        String eventName = (String) AOPHelper.getAnnotatedArg(point, EventName.class);

        CountableMethodHandler countableHandler = applicationContext.getBean(countable.handler());
        countableHandler.handle(new CountedEventMethodData(methodName, eventName));
    }

    @After("inEventService() && @annotation(countable) && @annotation(loggable) && methodWithAnnotationOnAtLeastOneParameter()")
    public void afterEventServiceCountableMethod(JoinPoint point, Countable countable, Loggable loggable) {
        String methodName = point.getSignature().getName();
        String eventName = (String) AOPHelper.getAnnotatedArg(point, EventName.class);

        CountableMethodHandler countableHandler = applicationContext.getBean(countable.handler());

        Integer count = countableHandler.getCount(new CountedEventMethodData(methodName, eventName));

        if(count >=10){
            LOGGER.info("Event {} for method {} accessed {} times!", eventName, methodName, count);
        }
    }
}
