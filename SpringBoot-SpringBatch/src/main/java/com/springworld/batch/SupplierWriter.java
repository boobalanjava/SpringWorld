package com.springworld.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springworld.batch.model.Supplier;
import com.springworld.batch.repository.SupplierRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SupplierWriter implements ItemWriter<Supplier> {

	@Autowired SupplierRepository repo;
	
	@Override
	public void write(List<? extends Supplier> supplierList) throws Exception {
		
		supplierList.forEach(supplier->{
			log.debug("::::::: inside Batch Writer ::::::::"+supplier.getSupplierId());
			repo.save(supplier);
		});
	}

}
