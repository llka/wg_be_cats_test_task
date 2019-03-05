package ru.ilka.wgforge.cats.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.ilka.wgforge.cats.service.filter.RequestLimitFilter;

@Configuration
@EnableWebMvc
@ComponentScan(value = "ru.ilka.wgforge.cats.service", excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "ru.ilka.wgforge.cats.service.config"))
@PropertySource("classpath:test_application.properties")
@TestPropertySource("classpath:resources/test_application.properties")
public class TestConfig {

    @Value("${limit.minute}")
    private long requestsLimitPerMinute;

    @Bean
    public FilterRegistrationBean<RequestLimitFilter> requestLimitFilter() {
        FilterRegistrationBean<RequestLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLimitFilter());
        registrationBean.addUrlPatterns("/");
        return registrationBean;
    }

    public long getRequestsLimitPerMinute() {
        return requestsLimitPerMinute;
    }
}
