package com.group4.auctionsite.configs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
class MyWebMvcConfigurer implements WebMvcConfigurer {
    private final String database = "hiddenGems.db";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:www/")
                .setCacheControl(CacheControl.noCache())
                .resourceChain(false);}
      //       .addResolver(new PathResourceResolver() {
      //           @Override
      //           protected Resource getResource(String resourcePath,
      //                                          Resource location) throws IOException {
      //               Resource requestedResource = location.createRelative(resourcePath);
      //               return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
      //                       : new ClassPathResource("/static/index.html");
      //           }
      //       }
      //        );
    //}

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/notFound").setViewName("forward:/index.html");
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        return container -> container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
                "/notFound"));
    }
    // this will enab
    // le CORS for all origins
    // disable in production
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:" + database);
        return dataSourceBuilder.build();
    }
}