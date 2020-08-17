package com.springworld.graphql.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.springworld.graphql.entity.SupplierData;
import com.springworld.graphql.fetcher.GetSupplierFetcher;
import com.springworld.graphql.fetcher.SupplierListFetcher;
import com.springworld.graphql.repository.SupplierRepository;


import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {
	
	@Autowired
	SupplierListFetcher supplierListFetcher;
	@Autowired
	GetSupplierFetcher  getSupplierFetcher;
	private GraphQL graphql;
	@Autowired
	SupplierRepository repo;
	
	@Value("classpath:supplier.graphql")
	Resource resource;
	
	
	@PostConstruct
	public void loadGraphQLSchema() throws IOException {
		loadDataToDB();
		File schemaFile=resource.getFile();
		TypeDefinitionRegistry typeRegistry=new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring= buildRunTimeWiring();
		GraphQLSchema schema=new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphql=GraphQL.newGraphQL(schema).build();
	}


	private void loadDataToDB() {
		Stream.of(
				new SupplierData(201l,"raja",new String[] {"LIC","StartHealth"},"India"),
				new SupplierData(202l,"Hari",new String[] {"Bajaj","HDFC Life"},"USA"),
				new SupplierData(203l,"Smitha",new String[] {"Axis Life","StartHealth"},"kenya")
				).forEach(supplier->{
					repo.save(supplier);
				});
		
	}

	private RuntimeWiring buildRunTimeWiring() {
		
		return RuntimeWiring.newRuntimeWiring()
				.type("Query",typeWiring ->typeWiring
						.dataFetcher("suppliertList", supplierListFetcher)
						.dataFetcher("supplierData",getSupplierFetcher))
						.build();
	}


	public GraphQL getGraphql() {
		return graphql;
	}

}
