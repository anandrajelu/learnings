package com.fiserv.batch;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.FormatterLineAggregator;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.fiserv.batch.listener.JobCompletionNotificationListener;
import com.fiserv.batch.model.Product;
import com.fiserv.batch.model.TransactionStreamData;
import com.fiserv.batch.processor.DisbursementProductProcessor;
import com.fiserv.batch.processor.TNProductProcessor;
import com.fiserv.batch.reader.DisbursementProductReader;
import com.fiserv.batch.reader.TNProductReader;

@Configuration
@Import({DbConfig.class})
@EnableBatchProcessing
@EnableScheduling
@ComponentScan(basePackages = "com.fiserv.batch")
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("mainComnJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private JobLauncher jobLauncher;

	@Scheduled(cron ="#{@getCronValue}")	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public void sendSmsForExpiringBookmark() throws Exception 
	{
		System.out.println(" Job Started at :"+ new Date());
		JobParameters param = new JobParametersBuilder().addString("JobID",
				String.valueOf(System.currentTimeMillis())).toJobParameters();
		JobExecution execution = jobLauncher.run(exportUserJob(), param);
		System.out.println("Job finished with status :" + execution.getStatus());
	}   
	
	@Bean("transactionData")
    public TransactionStreamData getTransactionData() {
      return new TransactionStreamData();
    }
	
	
	@Bean
	public String getCronValue(){
	    String sql = "SELECT PROPERTY_VALUE FROM CE_PROPERTIES WHERE HOME_ID=0 AND PROPERTY_NAME=?";

	    String cronExpression = (String) jdbcTemplate.queryForObject(
	            sql, new Object[] { "AML_CRON_EXPRESSION" }, String.class);
	    
	    System.out.println("AML_CRON_EXPRESSION==>"+cronExpression);
	    return cronExpression;
	}
	
	

	@Bean
	public JdbcCursorItemReader<Product> disbursementReader(){
		DisbursementProductReader reader =  new DisbursementProductReader();
		reader.setDataSource(dataSource);
		return reader;
	}

	@Bean
	public DisbursementProductProcessor disbursementProcessor(){
		return new DisbursementProductProcessor();
	}
	
	@Bean
	public JdbcCursorItemReader<Product> TNReader(){
		TNProductReader reader =  new TNProductReader();
		reader.setDataSource(dataSource);
		return reader;
	}
 
	@Bean
	public TNProductProcessor TNProcessor(){
		return new TNProductProcessor();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionNotificationListener();
	}

	@Bean
	public FlatFileItemWriter<Product> disbursementWriter(){
		String fileName = "C:\\sankar\\Shankar Vignesh\\Jan2018\\AML\\springboot-batch\\src\\main\\resources\\disbursment.txt";		
		FlatFileItemWriter<Product> writer = writeStream(fileName);
		return writer;	
	}
	
	@Bean
	public FlatFileItemWriter<Product> TNWriter(){
		String fileName = "C:\\sankar\\Shankar Vignesh\\Jan2018\\AML\\springboot-batch\\src\\main\\resources\\TN.txt";
		
		FlatFileItemWriter<Product> writer = writeStream(fileName);
		return writer;
	}

	private FlatFileItemWriter<Product> writeStream(String fileName) {
		FlatFileItemWriter<Product> writer = new FlatFileItemWriter<Product>();
		writer.setResource(new FileSystemResource(fileName));
		String format = "%-10s%-20s%-20s";
		writer.setLineAggregator(new FormatterLineAggregator<Product>() {{
			setFormat(format);
			setFieldExtractor(new BeanWrapperFieldExtractor<Product>() {{
				setNames(new String[] {"id","name","description"});
			}});
		}});
		return writer;
	}

	@Bean
	public Job exportUserJob() throws Exception {		
		Flow masterFlow = new FlowBuilder<Flow>("masterFlow").start(beforeStep()).build();   
		return jobBuilderFactory.get("exportUserJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.start(masterFlow).split(new SimpleAsyncTaskExecutor()).add(flow1(),flow2()).end().build();				
	}
	
	@Bean
    public Flow flow1() {
		return new FlowBuilder<Flow>("flow1").start(step1()).build();
	}
	
	@Bean
    public Flow flow2() {
		return new FlowBuilder<Flow>("flow2").start(step2()).build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Product, Product> chunk(10)
				.reader(disbursementReader())
				.processor(disbursementProcessor())
				.writer(disbursementWriter())
				.build();
	}
	
	@Bean
	public TaskletStep beforeStep() {
		return stepBuilderFactory.get("beforeStep").tasklet((contribution, chunkContext) -> 
		{System.out.println("Before Step");return RepeatStatus.FINISHED;}).build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").<Product, Product> chunk(10)
				.reader(TNReader())
				.processor(TNProcessor())
				.writer(TNWriter())
				.build();
	}

	/*@Bean
	public Job exportUserJob() {
		return jobBuilderFactory.get("exportUserJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.start(step1())
				.next(step2())				
				.build();
	}*/

}