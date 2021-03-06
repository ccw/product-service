package com.digitalriver.catalog.api;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.EnumSet;

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
@EnableAutoConfiguration
@PropertySource({"classpath:/application.properties"})
public class StoreServiceApp {

    private static final Logger logger = LoggerFactory.getLogger(StoreServiceApp.class);

    @Autowired
    protected Environment environment;

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

    public static void main(String[] args) {
        SpringApplication.run(StoreServiceApp.class, args);

        logger.debug("Application started");
    }
}
