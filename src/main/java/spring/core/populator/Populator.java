package spring.core.populator;

import org.springframework.core.convert.ConversionException;

public interface Populator<SOURCE, TARGET>
{
    void populate(SOURCE source, TARGET target) throws ConversionException;
}