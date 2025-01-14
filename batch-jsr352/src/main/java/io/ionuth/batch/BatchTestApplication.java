package io.ionuth.batch;

import java.util.UUID;

import javax.batch.operations.JobOperator;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BatchTestApplication {
	
	public static JobParameters createJobParams() {
		return new JobParametersBuilder()
				.addString("uuid", UUID.randomUUID().toString())
				.toJobParameters();
	}
	
	public static void runHelloWorld(ApplicationContext appContext) {
		JobLauncher jobLauncher = appContext.getBean(JobLauncher.class);
		Job job = (Job)appContext.getBean("helloWorldJob");
		try {
			jobLauncher.run(job, createJobParams());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void runHelloBatch(ApplicationContext appContext) {
		JobLauncher jobLauncher = appContext.getBean(JobLauncher.class);
		Job job = (Job)appContext.getBean("helloBatchJob");
		try {
			jobLauncher.run(job, createJobParams());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		ApplicationContext appContext = SpringApplication.run(BatchTestApplication.class, args);
		
		JobOperator jobOperator = appContext.getBean(JobOperator.class);
		jobOperator.start("helloBatchletJob", null);
		
		//DataSource ds = appContext.getBean(DataSource.class);
		//System.out.println("JDBC Template: " + ds.toString());
		
	}

}
