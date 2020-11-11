package com.lucianaugusto.mypetclinic.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass // Establishes this as a base class to JPA (No table is created in the DB)
public class BaseEntity implements Serializable {

//	Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Setting up this field as an Id to the JPA and setting up automated value generation
	private Long id;
	
	public boolean isNew() {
		return this.id == null;
	}
}
