package com.digitalriver.catalog.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.Environment;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.EnvironmentRepository;
import org.springframework.cloud.config.server.NativeEnvironmentRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;

/**
 * ConfigServerApp
 *
 * @author <a href="mailto:kchen@digitalriver.com">Ken Chen</a>
 * @since 2014.10
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigServer
@ComponentScan
public class ConfigServerApp {

    @Bean
    public EnvironmentRepository repository() {
        final String path = ConfigServerApp.class.getResource("/").getFile();
        return new EnvironmentRepository() {
            @Override
            public Environment findOne(final String application, final String name, final String label) {
                SpringApplicationBuilder builder = new SpringApplicationBuilder(PropertyPlaceholderAutoConfiguration.class);
                ConfigurableEnvironment environment = getEnvironment(name);
                builder.environment(environment);
                builder.web(false).showBanner(false);
                String[] args = getArgs(application);
                environment.getPropertySources().remove("profiles");
                try (ConfigurableApplicationContext context = builder.run(args)) {
                    return new NativeEnvironmentRepository(environment).findOne(application, name, label);
                }
            }

            protected ConfigurableEnvironment getEnvironment(String profile) {
                ConfigurableEnvironment environment = new StandardEnvironment();
                environment.getPropertySources()
                           .addFirst(new MapPropertySource("profiles",
                                                           Collections.<String, Object>singletonMap("spring.profiles.active", profile)));
                return environment;
            }

            protected String[] getArgs(String config) {
                List<String> list = new ArrayList<>();
                list.add("--spring.config.name=" + config);
                list.add("--spring.platform.bootstrap.enabled=false");
                list.add("--spring.config.location=" + path);
                return list.toArray(new String[0]);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApp.class, args);
    }

}
