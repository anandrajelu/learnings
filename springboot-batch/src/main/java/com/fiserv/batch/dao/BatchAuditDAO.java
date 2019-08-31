package com.fiserv.batch.dao;


import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BatchAuditDAO {

	@Autowired
	@Qualifier("bizFlowJdbcTemplate")
	public JdbcTemplate bizFlowJdbcTemplate;
	
	private static final String INSERT_NAV_CRON_DETAILS_WITH_CRON_PERIOD = "INSERT INTO NAV_CRON_DETAILS(CRON_ID, CRON_NAME, START_TIME, STATUS, CREATED_ON, CREATED_BY, COVERED_PERIOD_START_TIME, COVERED_PERIOD_END_TIME)  \n"
			+ " VALUES(?, ?, SYSDATE, 'INPROGRESS', SYSDATE, ?, ?, ?)";

	private static final String UPDATE_NAV_CRON_DETAILS = "UPDATE NAV_CRON_DETAILS SET STATUS = ?, END_TIME = SYSDATE \n"
			+ " WHERE CRON_ID  = ? ";
	
	
	
	/**
	 * Returns the sequence
	 * 
	 * @param tableName
	 * @return long
	 */
	public long getSequence(String tableName){
    	long sequence = 0;
    	
    	String sql = "SELECT " + tableName.toUpperCase() + "_SEQ.NEXTVAL SEQUENCE" + " FROM DUAL";
    	
		sequence = (Long) bizFlowJdbcTemplate.queryForObject(sql, Long.class);
		return sequence;
	}
	
	/**
	 * Populates the NAV_CRON_DETAILS table with Cron Run Time
	 * 
	 * @param adjustmentDTO 
	 * @param sequence
	 */
	public void popluateNavCronDetailsWithCronPeriod(long sequence, String cronName, Timestamp fromDate, Timestamp toDate){    	    	
		bizFlowJdbcTemplate.update(INSERT_NAV_CRON_DETAILS_WITH_CRON_PERIOD, sequence, cronName,cronName,fromDate,toDate);		
	}

	/**
	 * Updates the NAV_CRON_DETAILS table with Cron End Time
	 * 
	 * @param adjustmentDTO
	 * @param sequence
	 */
	public void updateNavCronDetails(long sequence, String status) {
		bizFlowJdbcTemplate.update(UPDATE_NAV_CRON_DETAILS, status, sequence);	
	}
	
	  
}
