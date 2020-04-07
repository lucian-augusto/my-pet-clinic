package com.lucianaugusto.mypetclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Owner extends Person {

//	Fields
	private String address;
	private String city;
	private String telephone;
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
