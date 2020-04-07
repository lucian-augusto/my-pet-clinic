package com.lucianaugusto.mypetclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person{

//	Fields
	private Set<Speciality> specialities = new HashSet<Speciality>(); // Initializing the Set of Pets with a default value to avoid null pointer errors

//	Getters and Setters
	public Set<Speciality> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(Set<Speciality> specialities) {
		this.specialities = specialities;
	}
	
	
}
