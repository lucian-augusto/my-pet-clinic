package com.lucianaugusto.mypetclinic.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "specialities")
public class Speciality extends BaseEntity {
	
//	Fields
	@Column(name = "description")
	private String description;
	
	@Builder
	public Speciality(Long id, String description) {
		super(id);
		this.description = description;
	}
	
	
}
