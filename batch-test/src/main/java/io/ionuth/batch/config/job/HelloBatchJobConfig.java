package io.ionuth.batch.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class HelloBatchJobConfig {
	
	private Step step(JobRepository jobRepository, 
			PlatformTransactionManager transactionManager) {
		
		Tasklet helloTasklet = (contribution, chunkContext) -> {
			System.out.println("Hello Batch from batch !!");
			return RepeatStatus.FINISHED;
		};
		
		return new StepBuilder("step01", jobRepository)
				.tasklet(helloTasklet, transactionManager)
				.build();
	}
	
	@Bean(name = "helloBatchJob")
	public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		var step = step(jobRepository, transactionManager);
		return new JobBuilder("helloBatchJob", jobRepository)
				.start(step)
				.build();
	}
}
