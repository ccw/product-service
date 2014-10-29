package com.digitalriver.catalog.api;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.EnumSet;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@ComponentScan
@MapperScan("com.digitalriver.catalog.api.mapper")
@EnableAutoConfiguration
@PropertySource({"classpath:/application.properties",
                 "classpath:/credential.properties"})
public class AdminServiceApp {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceApp.class);

    @Autowired
    protected Environment environment;

    @Autowired
    protected DataSource dataSource;

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        logger.info("Current active profiles: " + Arrays.toString(environment.getActiveProfiles()));

        return (ServletContext servletContext) -> {
            CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
            characterEncodingFilter.setEncoding("UTF-8");
            characterEncodingFilter.setForceEncoding(true);
            servletContext.addFilter(CharacterEncodingFilter.class.getSimpleName(), characterEncodingFilter)
                          .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
        };
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApp.class, args);

        logger.debug("Application started");
    }
}
