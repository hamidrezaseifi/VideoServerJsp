package de.seifi.videomanagerJsp.dao.helper;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
//@ComponentScan({ "com.spring.app" })
//@PropertySource("classpath:config/db.properties")
public class HibernateConfig {

  @Bean
  public LocalSessionFactoryBean sessionFactory() {

    final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

    sessionFactory.setDataSource(this.restDataSource());
    sessionFactory.setPackagesToScan(new String[] { "de.seifi.videomanagerJsp.models" });
    sessionFactory.setHibernateProperties(this.hibernateProperties());

    return sessionFactory;
  }

  @Bean
  public DataSource restDataSource() {

    final HikariConfig configuration = new HikariConfig();
    configuration.setAutoCommit(false);
    configuration.setPoolName("VIDEOMANAGER-DB-Pool");
    configuration.setDriverClassName("com.mysql.jdbc.Driver");
    configuration.setMinimumIdle(5);
    configuration.setJdbcUrl("jdbc:mysql://localhost:3306/jvideo");
    configuration.setIdleTimeout(120000L);
    configuration.setMaximumPoolSize(50);
    configuration.setUsername("jvideo");
    configuration.setPassword("jvideo");
    configuration.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
    configuration.setMaxLifetime(2000000);
    configuration.setConnectionTimeout(30000);

    final HikariDataSource ds = new HikariDataSource(configuration);
    ds.validate();
    return ds;
  }

  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(final SessionFactory sessionFactory) {

    final HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory);
    return txManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

    return new PersistenceExceptionTranslationPostProcessor();
  }

  Properties hibernateProperties() {

    return new Properties() {

      /**
      *
      */
      private static final long serialVersionUID = 1L;

      {
        this.setProperty("hibernate.hbm2ddl.auto", "none");
        this.setProperty("hibernate.show_sql", "false");
        this.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
      }
    };
  }
}
