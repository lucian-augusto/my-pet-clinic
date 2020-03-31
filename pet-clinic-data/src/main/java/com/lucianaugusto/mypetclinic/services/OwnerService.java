package com.lucianaugusto.mypetclinic.services;

import com.lucianaugusto.mypetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
	
	Owner findByLastName(String lasNameString);
}
