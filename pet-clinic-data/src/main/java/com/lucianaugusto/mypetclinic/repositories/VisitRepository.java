package com.lucianaugusto.mypetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lucianaugusto.mypetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}
