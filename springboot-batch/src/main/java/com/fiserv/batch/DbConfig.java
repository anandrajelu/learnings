package com.fiserv.batch;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
public class DbConfig {
	
	@Bean(destroyMethod = "")
	@Primary
	public DataSource dataSource() {
		DataSource dataSource = null;
		try{
			JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
			jndiDataSourceLookup.setResourceRef(true);
			dataSource = jndiDataSourceLookup.getDataSource("fundsTransferJTSDataSource");
			System.out.println("Maincomn DataSource Retrieved is "+(dataSource == null ? "null" : "not null"));
		}
		catch(Exception ex){
			System.out.println("Error retrieving Maincomn datasource via JNDI Look UP "+ex);
		}
		return dataSource;
	}
	
	@Bean(destroyMethod = "",name = "bizFlowDs")
	public DataSource bizFlowDataSource() {
		DataSource dataSource = null;
		try{
			JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
			jndiDataSourceLookup.setResourceRef(true);
			dataSource = jndiDataSourceLookup.getDataSource("bpmDataSource");
			System.out.println("BizFlow DataSource Retrieved is "+(dataSource == null ? "null" : "not null"));
		}
		catch(Exception ex){
			System.out.println("Error retrieving BizFlow datasource via JNDI Look UP "+ex);
		}
		return dataSource;
	}
	
	@Bean("mainComnJdbcTemplate")
	public JdbcTemplate getMainComnJdbcTemplate(){
		JdbcTemplate jdbcTemplate = null;
		try{
			jdbcTemplate = new JdbcTemplate(dataSource());
		}
		catch(Exception ex){
			System.out.println("Error creating JDBC Template "+ex);
		}
		return jdbcTemplate;
	}
	
	@Bean("bizFlowJdbcTemplate")
	public JdbcTemplate getBizFlowJdbcTemplate(){
		JdbcTemplate jdbcTemplate = null;
		try{
			jdbcTemplate = new JdbcTemplate(bizFlowDataSource());
		}
		catch(Exception ex){
			System.out.println("Error creating JDBC Template "+ex);
		}
		return jdbcTemplate;
	}
}
