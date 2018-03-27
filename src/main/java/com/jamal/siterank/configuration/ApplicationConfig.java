package com.jamal.siterank.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.jamal.siterank.dao.SiteRanksDao;

@Configuration
public class ApplicationConfig {
	
	  @Autowired 
	  Environment env;
	 

	@Bean
	public SiteRanksDao siteRankDao() {
		return new SiteRanksDao();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("datasource.driver"));

		dataSource.setUrl(System.getProperty("DB_CONNECTION"));

		dataSource.setUsername(System.getProperty("DB_USER"));

		dataSource.setPassword(System.getProperty("DB_PASSWORD"));

		return dataSource;

	}

}
