package com.lucianaugusto.mypetclinic.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass // Establishes this as a base class to JPA (No table is created in the DB)
public class BaseEntity implements Serializable {

//	Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Setting up this field as an Id to the JPA and setting up automated value generation
	private Long id;

	
//	Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
