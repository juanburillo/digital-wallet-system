package com.qaracter.digitalwallet.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration file for Swagger UI
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Digital Wallet & Transactions API") // API title
                .version("1.1") // API version
                .description("A digital wallet system that supports currency conversion, transactions, and scheduled payments.") // API description
        );
    }

}