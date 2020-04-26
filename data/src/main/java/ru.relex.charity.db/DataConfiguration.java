package ru.relex.charity.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("ru.relex.charity.db.mappers")
public class DataConfiguration {
}
