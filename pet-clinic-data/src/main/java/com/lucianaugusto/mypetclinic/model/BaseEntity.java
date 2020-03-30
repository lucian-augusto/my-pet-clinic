package com.lucianaugusto.mypetclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {

//	Fields
	private Long id;

	
//	Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
