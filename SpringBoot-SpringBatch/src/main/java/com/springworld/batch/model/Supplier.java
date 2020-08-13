package com.springworld.batch.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
public class Supplier {
	@Id
	@GeneratedValue
	Long supplierId;
	String code;
	String employeeType;
	String phoneNumber;
	String org;
	
}
