package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
  @Value("${jdbc.driver}")
  private String DRIVER;

  @Value("${jdbc.url}")
  private String URL;

  @Value("${jdbc.user}")
  private String USER;

  @Value("${jdbc.password}")
  private String PASSWORD;

  @Value("${hibernate.show_sql}")
  private String SHOW_SQL;

  @Value("${hibernate.packagesToScan}")
  private String PACKAGES_TO_SCAN;

  @Value("${hibernate.hbm2ddl.auto}")
  private String HBM2DDL_AUTO;

  @Value("${hibernate.current_session_context_class}")
  private String CURRENT_SESSION_CONTEXT_CLASS;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(DRIVER);
    dataSource.setUrl(URL);
    dataSource.setUsername(USER);
    dataSource.setPassword(PASSWORD);
    return dataSource;
  }

  @Bean(name = "entityManagerFactory")
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource());
    sessionFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.show_sql", SHOW_SQL);
    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
    hibernateProperties.setProperty("hibernate.current_session_context_class", CURRENT_SESSION_CONTEXT_CLASS);

    sessionFactoryBean.setHibernateProperties(hibernateProperties);

    return sessionFactoryBean;

  }

  @Bean
  public HibernateTransactionManager transactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }
}
