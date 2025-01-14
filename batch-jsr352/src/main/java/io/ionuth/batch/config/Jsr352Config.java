package io.ionuth.batch.config;

import javax.batch.operations.JobOperator;
import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.converter.JobParametersConverter;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.jsr.JsrJobParametersConverter;
import org.springframework.batch.core.jsr.launch.JsrJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

/*
 * In order to run Batch Jobs defined as per Jsr 352 standard from Spring Boot application
 * We need to configure the JsrJobOperator used to run batch job xml-s to use
 * the Spring Boot application context bean
 * 
 * We are injecting the beans created by SpringBoot into the JsrJobOperator
 * so it correctly configures its job repository, database connection datasource, etc
 */

@Configuration
@EnableBatchProcessing
public class Jsr352Config {
	
	@Bean
	public JobOperator jsrJobOperator(
			ApplicationContext applicationContext, 
			JobExplorer jobExplorer,
			JobRepository jobRepository, 
			JobParametersConverter jobParametersConverter,
			PlatformTransactionManager transactionManager) {

		JsrJobOperator jobOperator = new JsrJobOperator(
				jobExplorer, 
				jobRepository, 
				jobParametersConverter,
				transactionManager);
		jobOperator.setApplicationContext(applicationContext);
		jobOperator.setTaskExecutor(new SimpleAsyncTaskExecutor());

		return jobOperator;
	}
	
	@Bean
	public JobParametersConverter jobParametersConverter(DataSource dataSource) {
		return new JsrJobParametersConverter(dataSource);
	}
	

}
