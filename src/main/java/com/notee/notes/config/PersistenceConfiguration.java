package com.notee.notes.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
    @Bean
    @Profile("prod")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.mariadb.jdbc.Driver")
                .url("jdbc:mariadb://localhost/db")
                .username("root")
                .password("")
                .build();
    }
}
