package spring.core.aop;

import java.lang.annotation.Annotation;

import com.sun.istack.internal.NotNull;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.SoftException;
import org.aspectj.lang.reflect.MethodSignature;

public final class AOPHelper {

    public static Object getAnnotatedArg(@NotNull JoinPoint point, @NotNull Class<? extends Annotation>
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

    public static Object getArgWithType(@NotNull JoinPoint point, @NotNull Class<?>
            annotationType) {
        for (Object arg : point.getArgs()) {
            if (arg.getClass().isAssignableFrom(annotationType))
                return arg;
        }

        return null;
    }

}
