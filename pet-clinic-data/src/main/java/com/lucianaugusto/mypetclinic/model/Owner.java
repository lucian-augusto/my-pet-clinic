package com.lucianaugusto.mypetclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners") // Explicitly telling Hibernate to name the table related to this entity as "owners"
public class Owner extends Person {

//	Fields
	@Column(name = "address") // Setting the name of this column in the DB
	private String address;
	
	@Column(name = "city") // Setting the name of this column in the DB
	private String city;
	
	@Column(name = "telephone") // Setting the name of this column in the DB
	private String telephone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner") // Setting up the relationship between Owner and Pet
	private Set<Pet> pets = new HashSet<>(); // Initializing the Set of Pets with a default value to avoid null pointer errors

	@Builder
	public Owner(Long id, String firstName, String lastName, String address, String city, String telephone,
			Set<Pet> pets) {
		super(id, firstName, lastName);
		this.address = address;
		this.city = city;
		this.telephone = telephone;
		
		if (pets != null) {
			 this.pets = pets;
		}
	}

	public Pet getPet(String petName) {
		return getPet(petName, false);
	}
	
	public Pet getPet(String petName, boolean ignoreNew) {
		petName = petName.toLowerCase();
		
		for (Pet pet : pets) {
			
			if(!ignoreNew || !pet.isNew()) {
				String nameComparator = pet.getName().toLowerCase();
				
				if (nameComparator.equals(petName)) {
					return pet;
				}
			}
		}
		return null;
	}
}
