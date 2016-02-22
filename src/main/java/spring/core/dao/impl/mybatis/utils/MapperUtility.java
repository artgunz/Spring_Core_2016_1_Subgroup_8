package spring.core.dao.impl.mybatis.utils;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperUtility {
    @Autowired
    SqlSessionFactoryBean sqlSessionFactoryBean;

    public <T> T getMapperForType(Class<T> mapperTypeClass) throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
        return sessionTemplate.getMapper(mapperTypeClass);
    }
}
