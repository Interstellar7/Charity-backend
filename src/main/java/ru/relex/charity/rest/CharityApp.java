package ru.relex.charity.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.relex.charity.security.SecurityConfiguration;
import ru.relex.charity.services.ServicesConfiguration;

@SpringBootApplication(
        scanBasePackages = "ru.relex.charity.rest"
)
@Import({
        ServicesConfiguration.class,
        SecurityConfiguration.class
})
public class CharityApp {
    public static void main(String[] args) {
        SpringApplication.run(CharityApp.class, args);
    }
}
