package io.ionuth.batch;

import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BatchTestApplication {

	public static void main(String[] args) {
		
		// TODO check https://howtodoinjava.com/spring-batch/spring-boot-batch-tutorial-example/
		/*
		 * Spring batch relies on a database to store the job execution details. 
		 * Spring Batch will use the default DataSource available for initializing its schema, 
		 * if there is only one datasource available in the application.
		 * 
		 * If there are multiple datasources, we can annotate the bean method with @BatchDataSource 
		 * to explicitly define the datasource used by batch processes. 
		 * Do not forget to mark one datasource bean @Primary to avoid runtime errors.
		 */
		
		ApplicationContext appContext = SpringApplication.run(BatchTestApplication.class, args);
		
		runHelloWorldJob(appContext);
		//runAllConfiguredJobs();
		
	}
	
	private static void runHelloWorldJob(ApplicationContext appContext) {
		JobLauncher jobLauncher = appContext.getBean(JobLauncher.class);
		Job job = (Job)appContext.getBean("helloWorldJob");
		var jobParams = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();
		try {
			jobLauncher.run(job, jobParams);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void runAllConfiguredJobs() {
		// do nothing
	}

}
