package ru.ilka.wgforge.cats.service.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.ilka.wgforge.cats.service.filter.RequestLimitFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(value = "ru.ilka.wgforge.cats.service")
@PropertySource("classpath:application.properties")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public FilterRegistrationBean<RequestLimitFilter> limitFilter() {
        FilterRegistrationBean<RequestLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLimitFilter());
        registrationBean.addUrlPatterns("/");
        return registrationBean;
    }

    @Bean
    CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

    /* Swagger Documentation
    http://localhost:8088/api/v2/api-docs
    http://localhost:8088/api/swagger-ui.html#/
    */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.ilka.wgforge.cats.service.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Cats REST API",
                "Rest Api about cats, developed with Spring Boot and PostgreSQL",
                "1.0",
                "Terms of service",
                new Contact("Ilya Kisel", "https://github.com/llka/", "ilyakisel730@gmail.com"),
                "License of API", "https://github.com/llka/", Collections.emptyList());
    }
}
