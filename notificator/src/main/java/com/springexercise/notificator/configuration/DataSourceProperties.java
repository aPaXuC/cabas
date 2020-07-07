package com.springexercise.notificator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceProperties {
    @Bean(name = "mysqlDB")
    @ConfigurationProperties(prefix = "spring.mdatasource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "h2DB")
    @Primary
    @ConfigurationProperties(prefix = "spring.h2datasource")
    public DataSource h2DataSource() {
        return DataSourceBuilder.create().build();
    }
}
