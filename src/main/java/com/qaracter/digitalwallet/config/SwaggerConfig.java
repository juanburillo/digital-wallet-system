package com.qaracter.digitalwallet.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Digital Wallet & Transactions API")
                .version("1.0")
                .description("A digital wallet system that supports currency conversion, transactions, and scheduled payments.")
        );
    }

}