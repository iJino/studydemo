package com.liangjinhai.studydemo.config;

import com.liangjinhai.studydemo.filter.TimeFilter;
import com.liangjinhai.studydemo.interceptor.TimeInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Resource
//    private TimeInterceptor timeInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor).addPathPatterns("/test");
//    }
//
//    @Bean
//    public FilterRegistrationBean testTimeFilter(){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        TimeFilter timeFilter = new TimeFilter();
//        filterRegistrationBean.setFilter(timeFilter);
//        String[] list = {"/*"};
//        filterRegistrationBean.addUrlPatterns(list);
//        return filterRegistrationBean;
//    }

//    @Bean
//    public InternalResourceViewResolver getViewResolver(){
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("classpath:/templates");
//        viewResolver.setSuffix(".html");
//        return viewResolver;
//    }
}
