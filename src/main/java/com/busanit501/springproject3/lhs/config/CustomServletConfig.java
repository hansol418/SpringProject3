package com.busanit501.springproject3.lhs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CustomServletConfig implements WebMvcConfigurer {

    @Value("${uploadPath}")
    String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("lhs/js/**")
                .addResourceLocations("classpath:/static/lhs/js/");

        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/lhs/css/");

        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/static/fonts/");

        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/static/assets/");

        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");

        registry.addResourceHandler("/images/item/**")
                .addResourceLocations(uploadPath);
    }


}
