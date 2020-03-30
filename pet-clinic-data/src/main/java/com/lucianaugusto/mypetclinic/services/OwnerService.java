package com.lucianaugusto.mypetclinic.services;

import java.util.Set;

import com.lucianaugusto.mypetclinic.model.Owner;

public interface OwnerService {
	
	Owner findByLastName(String lasNameString);

	Owner findById(Long id);
	
	Owner save(Owner owner);
	
	Set<Owner> findAll();
}
