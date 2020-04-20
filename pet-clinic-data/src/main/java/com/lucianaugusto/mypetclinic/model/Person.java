package com.lucianaugusto.mypetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass // Establishes this as a base class to JPA (No table is created in the DB)
public class Person extends BaseEntity {

//	Fields
	@Column(name = "first_name") // Explicitly telling Hibernate how to name the column (snake_case)
	private String firstName;
	
	@Column(name = "last_name") // Explicitly telling Hibernate how to name the column (snake_case)
	private String lastName;

	public Person(Long id, String firstName, String lastName) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
}
