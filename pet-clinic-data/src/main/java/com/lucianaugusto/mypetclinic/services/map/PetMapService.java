package com.lucianaugusto.mypetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.lucianaugusto.mypetclinic.model.Pet;
import com.lucianaugusto.mypetclinic.services.PetService;

@Service // Using service and not @Component because it clearly states the behaviour of this service
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

	@Override
	public Set<Pet> findAll() {
		return super.findAll();
	}

	@Override
	public Pet findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public Pet save(Pet object) {
		return super.save(object);
	}

	@Override
	public void delete(Pet object) {
		super.delete(object);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
}