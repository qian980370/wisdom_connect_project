package com.project.wisdomconnect.common.config;

import com.project.wisdomconnect.common.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register");
//        .excludePathPatterns("/user/login", "/user/register", "/imserver/**", "/files/**", "/alipay/**",
//                "/doc.html", "/webjars/**", "/swagger-resources/**");

    }
    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }
}
