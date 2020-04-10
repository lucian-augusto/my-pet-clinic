package com.lucianaugusto.mypetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lucianaugusto.mypetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
