package com.lucianaugusto.mypetclinic.model;

import java.time.LocalDate;

public class Pet extends BaseEntity {

//	Fields
	private String name;
	private PetType petType;
	private Owner owner;
	private LocalDate birthDate;
	
//	Getters and Setters
	public PetType getPetType() {
		return petType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPetType(PetType petType) {
		this.petType = petType;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
}
