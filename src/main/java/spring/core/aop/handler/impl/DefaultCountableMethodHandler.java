package spring.core.aop.handler.impl;

import spring.core.aop.handler.CountableMethodHandler;
import spring.core.aop.handler.data.CountedMethodData;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DefaultCountableMethodHandler implements CountableMethodHandler {
    private final static Map<CountedMethodData, Integer> countMap = new HashMap<>();

    @Override
    public int getCount(final CountedMethodData countedMethodData) {
        return countMap.get(countedMethodData);
    }

    @Override
    public void handle(final CountedMethodData countedMethodData) {
        Integer count = countMap.get(countedMethodData);
        if (count == null) {
            count = 0;
        }

        count++;

        countMap.put(countedMethodData, count);
    }
}
