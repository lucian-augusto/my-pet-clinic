package com.lucianaugusto.mypetclinic.model;

import java.util.Set;

public class Owner extends Person {

//	Fields
	private String address;
	private String city;
	private String telephoneString;
	private Set<Pet> pets;
	
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
	public String getTelephoneString() {
		return telephoneString;
	}
	public void setTelephoneString(String telephoneString) {
		this.telephoneString = telephoneString;
	}
	public Set<Pet> getPets() {
		return pets;
	}
	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}
	
}
