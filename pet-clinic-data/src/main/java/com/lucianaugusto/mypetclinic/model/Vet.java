package com.lucianaugusto.mypetclinic.model;

import java.util.Set;

public class Vet extends Person{

//	Fields
	private Set<Speciality> specialities;

//	Getters and Setters
	public Set<Speciality> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(Set<Speciality> specialities) {
		this.specialities = specialities;
	}
	
	
}
