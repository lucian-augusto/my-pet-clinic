package com.lucianaugusto.mypetclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private Set<Pet> pets = new HashSet<Pet>(); // Initializing the Set of Pets with a default value to avoid null pointer errors
	
//	Getters and Setters
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephoneString) {
		this.telephone = telephoneString;
	}
	public Set<Pet> getPets() {
		return pets;
	}
	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}
	
}
