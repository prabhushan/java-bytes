package com.tista.aps.entity.ingestor;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
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

}
