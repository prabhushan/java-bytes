package com.tista.aps.entity.ingestor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application", ignoreInvalidFields = false, ignoreUnknownFields = true)
public class ApplicationProperties {
	private final Datasource datasource = new Datasource();

	public static class Datasource {
		private String url = null;
		private boolean showSql = false;
		private String userName = null;
		private String password = null;
		private String database = null;
		private String driverClassName = null;
		private String jpaDialect = null;
		private boolean generateDDL = false;
		private int connectionPoolSize;

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		/**
		 * @return the database
		 */
		public String getDatabase() {
			return database;
		}

		/**
		 * @param database
		 *            the database to set
		 */
		public void setDatabase(String database) {
			this.database = database;
		}

		/**
		 * @return the showSql
		 */
		public boolean isShowSql() {
			return showSql;
		}

		/**
		 * @param showSql
		 *            the showSql to set
		 */
		public void setShowSql(boolean showSql) {
			this.showSql = showSql;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getDriverClassName() {
			return driverClassName;
		}

		public void setDriverClassName(String driverClassName) {
			this.driverClassName = driverClassName;
		}

		public String getJpaDialect() {
			return jpaDialect;
		}

		public void setJpaDialect(String jpaDialect) {
			this.jpaDialect = jpaDialect;
		}

		/**
		 * @return the generateDDL
		 */
		public boolean isGenerateDDL() {
			return generateDDL;
		}

		/**
		 * @param generateDDL
		 *            the generateDDL to set
		 */
		public void setGenerateDDL(boolean generateDDL) {
			this.generateDDL = generateDDL;
		}

		public int getConnectionPoolSize() {
			return connectionPoolSize;
		}

		public void setConnectionPoolSize(int connectionPoolSize) {
			this.connectionPoolSize = connectionPoolSize;
		}
	}

	/**
	 * @return the datasource
	 */
	public Datasource getDatasource() {
		return datasource;
	}

}
