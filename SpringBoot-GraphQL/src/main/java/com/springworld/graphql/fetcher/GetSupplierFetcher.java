package com.springworld.graphql.fetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springworld.graphql.entity.SupplierData;
import com.springworld.graphql.repository.SupplierRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class GetSupplierFetcher implements DataFetcher<SupplierData>{

	@Autowired
	SupplierRepository repo;
	
	@Override
	public SupplierData get(DataFetchingEnvironment environment) {
		long supplierID=environment.getArgument("supplierID");
		SupplierData d=repo.findById(supplierID).get();
		System.out.println(d+d.getCountry());
		return repo.findById(supplierID).get();
	}

}
