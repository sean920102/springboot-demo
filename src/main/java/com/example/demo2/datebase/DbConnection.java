package com.example.demo2.datebase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

//@Configuration
public class DbConnection {

    //連線到 Myjdbc 資料庫的 DataSource 和 NamedParameterJdbcTemplate
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.myjdbc")
    public DataSource Myjdbc(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public NamedParameterJdbcTemplate MyjdbcTemplate(
            @Qualifier("Myjdbc") DataSource dataSource
    ){
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
