package ru.relex.charity.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.relex.charity.db.DataConfiguration;

@Configuration
@ComponentScan({
    "ru.relex.charity.services.mapstruct",
    "ru.relex.charity.services.service"
})
@Import(DataConfiguration.class)
public class ServicesConfiguration {
}
