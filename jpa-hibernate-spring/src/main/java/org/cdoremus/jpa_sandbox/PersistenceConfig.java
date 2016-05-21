package org.cdoremus.jpa_sandbox;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
	   @Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "org.cdoremus.jpa_sandbox.domain"});
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	 
	      return em;
	   }
	 
	   @Bean
	   public DataSource dataSource() {
	     return new EmbeddedDatabaseBuilder()
	             .setType(EmbeddedDatabaseType.H2)
//	             .addScript("schema.sql")
	             .build();
	   }
	   
	   @Bean
	   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(emf);
	 
	      return transactionManager;
	   }
	 
	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	      return new PersistenceExceptionTranslationPostProcessor();
	   }
	 
	   Properties additionalProperties() {
	      Properties properties = new Properties();
	      properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	      properties.setProperty("hibernate.show_sql", "true");
	      properties.setProperty("hibernate.format_sql", "true");
	      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	      return properties;
	   }

}
