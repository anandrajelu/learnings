package com.fiserv.batch.listener;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fiserv.batch.model.TransactionStreamData;
import com.fiserv.batch.dao.BatchAuditDAO;


/**
 * The Class JobCompletionNotificationListener
 *
 * @author 
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	
	@Autowired
	private BatchAuditDAO batchDAO;
	
	@Autowired 
	private TransactionStreamData transactionStream;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		
		long sequence = batchDAO.getSequence("NAV_CRON_DETAILS");
		System.out.println("Sequence"+sequence);		
		jobExecution.getExecutionContext().putLong("Sequence", sequence);
		batchDAO.popluateNavCronDetailsWithCronPeriod(sequence, "AML_TRANSACTION_STREAM_CRON", 
				new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
	}
	
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("Sequence"+jobExecution.getExecutionContext().getLong("Sequence"));				
			System.out.println("Home Size "+transactionStream.getHomeSet().size());
			batchDAO.updateNavCronDetails(jobExecution.getExecutionContext().getLong("Sequence"), "PASS");
		}
	}
}
