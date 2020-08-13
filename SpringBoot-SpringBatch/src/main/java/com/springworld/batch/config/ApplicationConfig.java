package com.springworld.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.springworld.batch.SupplierProcessor;
import com.springworld.batch.SupplierWriter;
import com.springworld.batch.model.Supplier;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableBatchProcessing
@Slf4j
public class ApplicationConfig {
	
	@Autowired JobBuilderFactory jobBuilderFactory;
	@Autowired StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("springWorldBatchJob")
		  .start(step())
		  .build();
		
	}
	@Bean
	public Step step() { 
		return stepBuilderFactory.get("springWorldBatchStep")
				.<Supplier,Supplier>chunk(5)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build(); 
	}
	@Bean
	@StepScope
	public ItemWriter<Supplier> writer() {
		return new SupplierWriter();
	}
	@Bean
	@StepScope
	public ItemProcessor<Supplier,Supplier> processor() {
		return new SupplierProcessor();
	}
	@Bean
	@StepScope
	public FlatFileItemReader<Supplier> reader() {
		FlatFileItemReader<Supplier> flatFileItemReader = new FlatFileItemReader<Supplier>();
		log.debug("::::::::: inside Batch reader ::::::::::"); 
		flatFileItemReader.setResource(new FileSystemResource("src\\main\\resources\\supplier_data.csv")); 
		flatFileItemReader.setName("csv-file-reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}
	@Bean
	public LineMapper<Supplier> lineMapper() {
		 DefaultLineMapper<Supplier> defaultLineMapper = new DefaultLineMapper<>();
	        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
	        lineTokenizer.setDelimiter(",");
	        lineTokenizer.setStrict(false);
	        lineTokenizer.setNames(new String[]{"supplierId", "code", "employeeType", "phoneNumber"});
	        BeanWrapperFieldSetMapper<Supplier> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
	        fieldSetMapper.setTargetType(Supplier.class); 
	        
	        defaultLineMapper.setLineTokenizer(lineTokenizer);
	        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
	        
		return defaultLineMapper;
	}

}
