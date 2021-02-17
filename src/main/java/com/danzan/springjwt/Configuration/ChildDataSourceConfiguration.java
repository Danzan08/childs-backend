package com.danzan.springjwt.Configuration;

import com.danzan.springjwt.Childs.models.Child;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.danzan.springjwt.Childs.repository",
        entityManagerFactoryRef = "childEntityManagerFactory",
        transactionManagerRef = "childTransactionManager")
public class ChildDataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.childs")
    public DataSourceProperties childDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.childs.configuration")
    public DataSource childDataSource() {
        return childDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Primary
    @Bean(name = "childEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean childEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(childDataSource())
                .packages(Child.class)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager childTransactionManager(
            final @Qualifier("childEntityManagerFactory") LocalContainerEntityManagerFactoryBean childEntityManagerFactory) {
        return new JpaTransactionManager(childEntityManagerFactory.getObject());
    }
}
