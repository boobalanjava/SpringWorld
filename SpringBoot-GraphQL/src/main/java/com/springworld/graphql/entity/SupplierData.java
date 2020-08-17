package com.springworld.graphql.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@Entity
@Table
@NoArgsConstructor
@ToString
public class SupplierData {

	@Id
	@GeneratedValue
	long supplierID;
	String customerName;
	String [] products;
	String country;
}
