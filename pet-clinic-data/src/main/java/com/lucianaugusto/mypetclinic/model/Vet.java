package com.lucianaugusto.mypetclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vets")
public class Vet extends Person {

//	Fields
	@ManyToMany(fetch = FetchType.EAGER) // Setting up a many-to-many relationship between Vet and Speciality and changing the fetch type
	// to Eager (Loads everything at once)
	@JoinTable(name = "vet_specialities", joinColumns = @JoinColumn(name = "vet_id"),
			inverseJoinColumns = @JoinColumn(name = "speciality_id")) // Setting up the mapping between the two entity tables and how the
			// relationship is defined at the DB level
	private Set<Speciality> specialities = new HashSet<Speciality>(); // Initializing the Set of Pets with a default value to avoid null pointer errors

	@Builder
	public Vet(Long id, String firstName, String lastName, Set<Speciality> specialities) {
		super(id, firstName, lastName);
		this.specialities = specialities;
	}
}
