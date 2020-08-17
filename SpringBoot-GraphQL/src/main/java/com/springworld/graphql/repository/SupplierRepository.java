package com.springworld.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springworld.graphql.entity.SupplierData;

public interface SupplierRepository extends JpaRepository<SupplierData, Long>{

} 
