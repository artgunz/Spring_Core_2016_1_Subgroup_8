package spring.core.aop.handler;

import spring.core.aop.handler.data.CountedMethodData;

public interface CountableMethodHandler {
    int getCount(CountedMethodData countedMethodData);

    void handle(CountedMethodData countedMethodData);
}
