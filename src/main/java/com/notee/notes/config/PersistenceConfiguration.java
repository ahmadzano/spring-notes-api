package com.notee.notes.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
    @Bean
    @Profile("dev")
    public DataSource primaryDataSource() {

        return DataSourceBuilder.create()
                .url("jdbc:h2:file:~/default")
                .username("sa")
                .password("")
                .build();
    }

    @Bean
    @Profile("prod")
    public DataSource secondaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://localhost/db");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
}
