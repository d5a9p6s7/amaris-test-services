/*
 * CasApp.
 * Copyright 2021.
 */
package com.amaris.test.product.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:dpoveda@gmail.com">Diego Poveda</a>
 * @version 1.0-SNAPSHOT 2021/10/27
 * @since 1.8
 */
@Slf4j
@Configuration
public class OpenAPIConfiguration {

  @Bean
  public OpenAPI customOpenAPI() {

    return new OpenAPI()
        .openapi("3.0.1")
        .components(new Components())
        .info(new Info()
            .title("Amaris Test Services API")
            .description("This is a Spring Boot Application using springdoc-openapi and OpenAPI 3."));
  }
}
