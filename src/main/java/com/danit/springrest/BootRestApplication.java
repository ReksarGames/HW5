package com.danit.springrest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class BootRestApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BootRestApplication.class, args);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override public void run(ApplicationArguments args) throws Exception {
        System.out.println("http://localhost:9000/swagger-ui/index.html");
        System.out.println("http://localhost:9000/h2-console");
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("EIS API")
                        .description("Employee Information System sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .description("SpringShop Wiki Documentation")
                        .contact(new Contact().email("test@test.com").url("http://fullstackcode.dev")))
                ;
    }
}
