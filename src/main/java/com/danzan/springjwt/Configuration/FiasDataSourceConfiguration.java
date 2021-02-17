package com.danzan.springjwt.Configuration;

import com.danzan.springjwt.Fias.model.Fias;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.danzan.springjwt.Fias.repository",
        entityManagerFactoryRef = "fiasEntityManagerFactory",
        transactionManagerRef = "fiasTransactionManager")
public class FiasDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("app.datasource.fias")
    public DataSourceProperties fiasDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.fias.configuration")
    public DataSource fiasDataSource() {
        return fiasDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean(name = "fiasEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean fiasEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(fiasDataSource())
                .packages(Fias.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager fiasTransactionManager(
            final @Qualifier("fiasEntityManagerFactory") LocalContainerEntityManagerFactoryBean fiasEntityManagerFactory) {
        return new JpaTransactionManager(fiasEntityManagerFactory.getObject());
    }

}
