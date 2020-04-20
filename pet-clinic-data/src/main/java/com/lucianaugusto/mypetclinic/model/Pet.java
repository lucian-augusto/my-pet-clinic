package com.lucianaugusto.mypetclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Builder
@Entity
@Table(name = "pets") // Explicitly telling Hibernate to name the table related to this entity as "pets"
public class Pet extends BaseEntity {

//	Fields
	@Column(name = "name") // Setting the name of this column in the DB
	private String name;
	
	@ManyToOne // Setting up the relationship between Pet and PetType
	@JoinColumn(name = "type_id")
	private PetType petType;
	
	@ManyToOne // Setting up the other side of the relationship between Owner and Pet
	@JoinColumn(name = "owner_id") // Adding an Owner Id on the pet record
	private Owner owner;
	
	@Column(name = "birth_date") // Setting the name of this column in the DB
	private LocalDate birthDate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
	private Set<Visit> visits = new HashSet<Visit>();
	
}
