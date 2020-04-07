package com.lucianaugusto.mypetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.model.Pet;
import com.lucianaugusto.mypetclinic.services.OwnerService;
import com.lucianaugusto.mypetclinic.services.PetService;
import com.lucianaugusto.mypetclinic.services.PetTypeService;

@Service // Using service and not @Component because it clearly states the behaviour of this service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
	
//	Fields
	private final PetTypeService petTypeService;
	private final PetService petService;
	
//	Constructor
	public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
	super();
	this.petTypeService = petTypeService;
	this.petService = petService;
}
	

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
		if (object != null) {
			if (object.getPets() != null) {
				object.getPets().forEach(pet -> { // Checking every pet. Here we're looping for every pet present in the set (that belongs
					// to this specific owner)
					if (pet.getPetType() != null) { // Checks if all the pets have a type
						if (pet.getPetType().getId() == null) { // Saves the pet type if it's not present in the data yet
							pet.setPetType(petTypeService.save(pet.getPetType()));
						}
					} else {
						throw new RuntimeException("Pet Type is required."); // If the Pet doesn't have a type, it throws an exception
					}
					
					if (pet.getId() == null) { // Checks if the pet is already saved on the data
						Pet savedPet = petService.save(pet); // Saves the pet if it's not saved already
						pet.setId(savedPet.getId()); // Makes sure that we're saving the pet and setting its Id.
					}
				});
			}
			return super.save(object);
		} else {
			return null;
		}
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
