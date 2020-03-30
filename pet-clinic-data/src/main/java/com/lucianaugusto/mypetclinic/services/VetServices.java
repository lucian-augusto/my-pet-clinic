package com.lucianaugusto.mypetclinic.services;

import java.util.Set;

import com.lucianaugusto.mypetclinic.model.Vet;

public interface VetServices {

	Vet findById(Long id);
	
	Vet save(Vet vet);
	
	Set<Vet> findAll();
}
