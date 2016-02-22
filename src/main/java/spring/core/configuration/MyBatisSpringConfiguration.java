package spring.core.configuration;


import spring.core.dao.impl.mybatis.mapper.EventMapper;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class MyBatisSpringConfiguration {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("spring.core.dao.impl.mybatis.*");
        return mapperScannerConfigurer;
    }

    @Bean
    @Autowired
    public SqlSessionFactoryBean sqlSessionFactoryBean(final DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.getObject().getConfiguration().addMapper(EventMapper.class);
        return sqlSessionFactoryBean;
    }

//    @Bean
//    @Autowired
//    @DependsOn(value = "sqlSessionFactoryBean")
//    public EventMapper eventMapper(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
//        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
//        return sessionTemplate.getMapper(EventMapper.class);
//    }
}
