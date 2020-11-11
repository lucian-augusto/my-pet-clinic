package com.lucianaugusto.mypetclinic.services;

import java.util.List;

import com.lucianaugusto.mypetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
	
	Owner findByLastName(String lasNameString);
	
	List<Owner> findAllByLastNameLike(String lastName);
}
