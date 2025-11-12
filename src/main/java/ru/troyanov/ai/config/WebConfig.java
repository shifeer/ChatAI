package ru.troyanov.ai.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
        var filterReg = new  FilterRegistrationBean<HiddenHttpMethodFilter>();
        filterReg.setFilter(new HiddenHttpMethodFilter());
        return filterReg;
    }
}
