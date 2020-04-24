package com.lucianaugusto.mypetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.lucianaugusto.mypetclinic.model.Speciality;
import com.lucianaugusto.mypetclinic.model.Vet;
import com.lucianaugusto.mypetclinic.services.SpecialityService;
import com.lucianaugusto.mypetclinic.services.VetService;

@Service // Using service and not @Component because it clearly states the behaviour of this service
@Profile({"default", "map"}) // Annotating the Map Services as default (explicitly) and giving it its own profile name
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {
	
	private final SpecialityService specialityService;
	
	public VetMapService(SpecialityService specialityService) {
		this.specialityService = specialityService;
	}
	
	
	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public Vet save(Vet object) {
		if (object.getSpecialities() != null) { // Checks if the Vet that is going to be saved has any speciality
			object.getSpecialities().forEach(speciality -> { // Loops through all the specialities that the saved vet has
				if (speciality.getId() == null) { // If one (or more) of the speicialities doesn't have a id value (doesn't exist already)
					Speciality savedSpeciality = specialityService.save(speciality); // Saves this new Speciality
					speciality.setId(savedSpeciality.getId()); // And sets a new Id value fo it
				}
			});
		}
		return super.save(object);
	}

	@Override
	public void delete(Vet object) {
		super.delete(object);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	
}
