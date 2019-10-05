package com.ts.tsadmin.conf;

import com.ts.tsadmin.domain.EntityMarker;
import com.ts.tsadmin.repository.RepoMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "tsAdminEntityManagerFactory",
        transactionManagerRef = "tsAdminTransactionManager",
        basePackageClasses = RepoMarker.class)
public class TsAdminDbConfig {
    @Autowired
    @Bean
    PlatformTransactionManager tsAdminTransactionManager(@Qualifier("tsAdminEntityManagerFactory")
                                                                 EntityManagerFactory tsAdminEntityManagerFactory) {
        return new JpaTransactionManager(tsAdminEntityManagerFactory);
    }

    @Autowired
    @Bean(name = "tsAdminEntityManagerFactory")
    EntityManagerFactory tsAdminEntityManagerFactory(@Qualifier("tsAdminDataSource") DataSource tsAdminDataSource) {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("tsAdmin");
        factoryBean.setDataSource(tsAdminDataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(EntityMarker.class.getPackage().getName());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Primary
    @Bean("tsAdminSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties tsAdminSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "tsAdminDataSource")
    public DataSource dataSource(@Qualifier("tsAdminSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

//    @Autowired
//    @Bean
//    public AuditorAware<User> auditorAware(Db1UserRepository db1UserRepository) {
//        return new Db1AuditorAwareImpl(db1UserRepository);
//    }

}
