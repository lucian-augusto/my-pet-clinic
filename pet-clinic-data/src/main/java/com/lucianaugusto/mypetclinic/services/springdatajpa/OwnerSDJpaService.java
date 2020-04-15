package com.lucianaugusto.mypetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.repositories.PetRepository;
import com.lucianaugusto.mypetclinic.repositories.PetTypeRepository;
import com.lucianaugusto.mypetclinic.services.OwnerService;
import com.lucianaugusto.mypetclinic.services.map.OwnerServiceMap;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {
	
	private final OwnerServiceMap ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;
	
	
	public OwnerSDJpaService(OwnerServiceMap ownerRepository, PetRepository petRepository,
			PetTypeRepository petTypeRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Set<Owner> findAll() {
		Set<Owner> owners = new HashSet<Owner>();
		
		ownerRepository.findAll().forEach(owners::add);
		return owners;
	}

	@Override
	public Owner findById(Long id) {
		Optional<Owner> optionalOwner = ownerRepository.findById(id);
		return optionalOwner.orElse(null);
	}

	@Override
	public Owner save(Owner object) {
		return ownerRepository.save(object);
	}

	@Override
	public void delete(Owner object) {
		ownerRepository.delete(object);
		
	}

	@Override
	public void deleteById(Long id) {
		ownerRepository.deleteById(id);		
	}

	@Override
	public Owner findByLastName(String lasNameString) {
		return ownerRepository.findByLastName(lasNameString);
	}
	
	

}
