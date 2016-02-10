package spring.core.aop.annotation;

import spring.core.aop.handler.CountableMethodHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Countable {
    Class<? extends CountableMethodHandler> handler();
}
