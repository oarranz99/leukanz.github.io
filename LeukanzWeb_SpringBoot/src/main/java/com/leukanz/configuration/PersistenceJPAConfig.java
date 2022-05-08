package com.leukanz.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.leukanz.domain.Ilustracion;
import com.leukanz.utils.ImgConfigUtils;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {Ilustracion.class,ImgConfigUtils.class})
public class PersistenceJPAConfig {

	@Bean
	public DataSource dataSource(){
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/leukanz");
	    dataSource.setUsername( "root" );
	    dataSource.setPassword( "" );
	    return dataSource;
	}
	
	   @Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "com.leukanz.domain" });
	      final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	        em.setJpaVendorAdapter(vendorAdapter);
	        final HashMap<String, Object> properties = new HashMap<String, Object>();
	        properties.put("hibernate.hbm2ddl.auto", "update");
	        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	        em.setJpaPropertyMap(properties);
	      return em;
	      
	   }
	   
	 @Bean
	 public PlatformTransactionManager transactionManager() {
	     JpaTransactionManager transactionManager = new JpaTransactionManager();
	     transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

	     return transactionManager;
	 }

	 @Bean
	 public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	     return new PersistenceExceptionTranslationPostProcessor();
	 }

	 
}
