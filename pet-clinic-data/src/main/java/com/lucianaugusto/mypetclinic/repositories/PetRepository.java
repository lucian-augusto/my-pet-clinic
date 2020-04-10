package com.lucianaugusto.mypetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lucianaugusto.mypetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
