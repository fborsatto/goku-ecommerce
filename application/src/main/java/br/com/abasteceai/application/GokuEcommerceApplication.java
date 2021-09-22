package br.com.abasteceai.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "br.com.abasteceai")
@EnableMongoRepositories(basePackages = "br.com.abasteceai")
@EnableSwagger2
@EnableCaching
public class GokuEcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GokuEcommerceApplication.class, args);
    }
}
