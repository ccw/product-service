package com.digitalriver.catalog.api.configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ApiDocConfiguration
 *
 * @author <a href="mailto:kchen@digitalriver.com">Ken Chen</a>
 * @since 2014.10
 */
@Configuration
@EnableSwagger
public class ApiDocConfiguration {

    @Autowired
    protected SpringSwaggerConfig springSwaggerConfig;

    @Bean
    public SwaggerSpringMvcPlugin customImplementation(){
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                       .apiInfo(new ApiInfo("Product Admin API",
                                            "The API for product administration",
                                            "https://gc.digitalriver.com/store/defaults/en_US/DisplayDRTermsAndConditionsPage/eCommerceProvider.Digital+River,+Inc.",
                                            "service@digitalriver.com",
                                            "Digital River",
                                            "https://gc.digitalriver.com/store/defaults/en_US/DisplayDRTermsAndConditionsPage/eCommerceProvider.Digital+River,+Inc."))
                       .apiVersion("0.0.1")
                       .includePatterns(".*solr.*",
                                        ".*health.*",
                                        ".*beans.*",
                                        ".*info.*",
                                        ".*metrics.*",
                                        ".*trace.*",
                                        ".*configprops.*",
                                        ".*shutdown.*");
    }


}
