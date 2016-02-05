package spring.core.strategy;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

@Component
public class UserIdGeneratorStrategy {
    public Long generateId(){
        return RandomUtils.nextLong(3495050, 254680339);
    }
}
