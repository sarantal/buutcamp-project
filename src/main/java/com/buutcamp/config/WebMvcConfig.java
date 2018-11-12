package com.buutcamp.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


@Configuration
@ComponentScan("com.buutcamp")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:webconfig.properties")
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${prefix}")
    private String prefix;

    @Value("${suffix}")
    private String suffix;


    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setPrefix(prefix);
        urlBasedViewResolver.setSuffix(suffix);
        urlBasedViewResolver.setViewClass(JstlView.class);

        return urlBasedViewResolver;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        //set sessionfactory data source
        sessionFactory.setDataSource(myDataSource());

        //tell hibernate where to find classes to manage
        sessionFactory.setPackagesToScan("com.buutcamp.entities");

        //set hibernate properties
        sessionFactory.setHibernateProperties(hibernateProperties());

        //return sessionfactory
        return sessionFactory;
    }


    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }


    @Bean
    public DataSource secDataSource() {

        BasicDataSource secDataSource = new BasicDataSource();
        //com.mysql.cj.jdbc.Driver
        secDataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        //jdbc:mysql://localhost:3306/security_demo?serverTimeZone=UTC&allowPublicKeyRetrieval=true
        secDataSource.setUrl("jdbc:mariadb://localhost:3306/dev_db");
        secDataSource.setUsername("devuser");
        secDataSource.setPassword("devuser");

        secDataSource.setInitialSize(1);

        secDataSource.setMaxConnLifetimeMillis(10000000);

        secDataSource.setMaxTotal(6);

        return secDataSource;
    }


    DataSource myDataSource() throws SQLException {

        //create datasource
        MariaDbDataSource dataSource = new MariaDbDataSource();

        //set connection info
        dataSource.setDatabaseName("dev_db");
        dataSource.setUserName("devuser");
        dataSource.setPassword("devuser");

        //return datasource
        return dataSource;
    }


    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
            }
        };
    }

}
