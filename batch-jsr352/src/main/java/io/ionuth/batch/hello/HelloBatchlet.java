package io.ionuth.batch.hello;

import java.util.Optional;
import java.util.Properties;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.StepContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import io.ionuth.batch.dao.DepartmentDao;
import io.ionuth.batch.ito.impl.Department;


public class HelloBatchlet implements Batchlet {
	
	@Autowired
	private StepContext stepContext;
	
	@Autowired
	private DepartmentDao deptDao;
	
	@Override
	public String process() throws Exception {
		
		System.out.println("HelloBatchlet processing ...  Hello from Batchlet");
		
		Properties properties = stepContext.getProperties();

        // Retrieve specific properties
        String inputFile = properties.getProperty("inputFile");
        String retryLimit = properties.getProperty("retryLimit");

        // Log or use the properties
        System.out.println("Input File: " + inputFile);
        System.out.println("Retry Limit: " + retryLimit);
		
        System.out.println("----------------");
        
        Optional<Department> opt = deptDao.selectById(1);
        if(opt.isPresent()) {
        	System.out.println("Found department: " + opt.get());
        }
        
        System.out.println("----------------");
        
		return String.valueOf(0);
	}

	@Override
	public void stop() throws Exception {
		System.out.println("HelloBatchlet stoped");
		
	}

}
