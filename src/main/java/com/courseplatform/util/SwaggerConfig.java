package com.courseplatform.util;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author chen cy
 * Created by ye on 2016/12/12.
 */
@Configuration
@EnableSwagger
@EnableWebMvc
public class SwaggerConfig {

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*?");
    }

    private ApiInfo apiInfo() {
        String title = "课程平台API";
        String descridption = "My Apps API Descridption";
        String termsOfServiceUrl = "My Apps API terms of service";
        String contact = "My Apps API Contact Email";
        String licence = "My Apps API Licence Type";
        String licenceUrl = "My Apps API Licence URL";
        ApiInfo apiInfo = new ApiInfo(title, descridption, termsOfServiceUrl, contact, licence, licenceUrl);
        return apiInfo;
    }

}
