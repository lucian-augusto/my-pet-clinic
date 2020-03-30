package com.lucianaugusto.mypetclinic.services;

import java.util.Set;

import com.lucianaugusto.mypetclinic.model.Pet;

public interface PetService {

	Pet findById(Long id);
	
	Pet save(Pet pet);
	
	Set<Pet> findAll();
}
