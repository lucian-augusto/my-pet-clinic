package com.lucianaugusto.mypetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.services.OwnerService;

@Service // Using service and not @Component because it clearly states the behaviour of this service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}
	
	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public Owner save(Owner object) {
		return super.save(object.getId(), object);
	}

	@Override
	public void delete(Owner objetct) {
		super.delete(objetct);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Owner findByLastName(String lasNameString) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
