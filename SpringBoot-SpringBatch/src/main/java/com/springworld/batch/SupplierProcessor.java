package com.springworld.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.springworld.batch.model.Supplier;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SupplierProcessor implements ItemProcessor<Supplier, Supplier> {

	@Override
	public Supplier process(Supplier supplier) throws Exception {
		String org=supplier_config.get(String.valueOf(supplier.getSupplierId()));
		supplier.setOrg(org); 
		 log.debug(":::::::: inside Spring batch Processor ::::"+supplier.getSupplierId());
		return supplier;
	}

	 private static final Map<String, String> supplier_config =
	            new HashMap<>();

	    public SupplierProcessor() {
	        supplier_config.put("4523", "LIC Insurence");
	        supplier_config.put("4524", "Star Health");
	        supplier_config.put("4526", "Bajaj Aliance");
	    }
}
