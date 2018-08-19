package com.prabhu.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories("com.prabhu.hibernate.repository")
@EnableTransactionManagement
// @EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class DatabaseConfiguration {

	private final Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);

	@Autowired
	private ApplicationProperties applicationProperties;

	// CMA: Added for Datasource
	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(applicationProperties.getDatasource().getUrl());
		config.setUsername(applicationProperties.getDatasource().getUserName());
		config.setPassword(applicationProperties.getDatasource().getPassword());
		config.setMaximumPoolSize(applicationProperties.getDatasource().getConnectionPoolSize());

		return new HikariDataSource(config);
	}

	private HibernateJpaVendorAdapter getJPAVendor() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

		jpaVendorAdapter.setDatabase(Database.valueOf(applicationProperties.getDatasource().getDatabase()));
		jpaVendorAdapter.setGenerateDdl(applicationProperties.getDatasource().isGenerateDDL());
		jpaVendorAdapter.setShowSql(applicationProperties.getDatasource().isShowSql());
		jpaVendorAdapter.setDatabasePlatform(applicationProperties.getDatasource().getJpaDialect());
		logger.debug("JPA Vendor Adapter initiated successfully");
		return jpaVendorAdapter;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter jpaVendorAdapter = getJPAVendor();

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan("com.prabhu.hibernate");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

}
