package com.springworld.batch.controller;



import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/springWorldBatch")
@Slf4j
public class SpringBatchController {
	
	@Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;
    
    @GetMapping("/triggerSpringBatch")
    public BatchStatus runBatch() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
    	
         JobParameters parameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
         
         JobExecution jobExecution = jobLauncher.run(job, parameters);

         log.debug("JobExecution: " + jobExecution.getStatus());

         System.out.println("Batch is Running...");
         while (jobExecution.isRunning()) {
        	 log.debug(":::::::: job not yet completed ::::::::");
         }
		return jobExecution.getStatus(); 
    }

}
