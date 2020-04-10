package com.lucianaugusto.mypetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lucianaugusto.mypetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
