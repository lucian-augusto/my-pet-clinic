package com.lucianaugusto.mypetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass // Establishes this as a base class to JPA (No table is created in the DB)
public class Person extends BaseEntity {

//	Fields
	@Column(name = "first_name") // Explicitly telling Hibernate how to name the column (snake_case)
	private String firstName;
	
	@Column(name = "last_name") // Explicitly telling Hibernate how to name the column (snake_case)
	private String lastName;
	
	
//	Getters and Setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
