package spring.core.aop;

import java.lang.annotation.Annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.SoftException;
import org.aspectj.lang.reflect.MethodSignature;

public final class AOPHelper {

    public static Object getAnnotatedArg(JoinPoint point, Class<? extends Annotation>
            annotationType) {
        String methodName = point.getSignature().getName();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        Annotation[][] annotations;
        try {
            annotations = point.getTarget().getClass().getMethod(methodName, parameterTypes).getParameterAnnotations();
        } catch (Exception e) {
            throw new SoftException(e);
        }

        int i = 0;
        for (Object arg : point.getArgs()) {
            for (Annotation annotation : annotations[i]) {
                if (annotation.annotationType() == annotationType)
                    return arg;
            }
            i++;
        }

        return null;
    }

    public static <T> T getArgWithType(JoinPoint point, Class<T>
            annotationType) {
        for (Object arg : point.getArgs()) {
            if (arg!=null && arg.getClass().isAssignableFrom(annotationType))
                return (T) arg;
        }

        return null;
    }

}
