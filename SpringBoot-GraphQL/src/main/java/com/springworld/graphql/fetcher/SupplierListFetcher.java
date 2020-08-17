package com.springworld.graphql.fetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springworld.graphql.entity.SupplierData;
import com.springworld.graphql.repository.SupplierRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class SupplierListFetcher implements DataFetcher<List<SupplierData>>{

	@Autowired
	SupplierRepository repo;
	
	@Override
	public List<SupplierData> get(DataFetchingEnvironment environment) {
		return repo.findAll(); 
	}

}
