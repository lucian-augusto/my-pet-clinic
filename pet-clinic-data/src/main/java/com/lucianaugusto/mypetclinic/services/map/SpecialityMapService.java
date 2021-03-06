package com.lucianaugusto.mypetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.lucianaugusto.mypetclinic.model.Speciality;
import com.lucianaugusto.mypetclinic.services.SpecialityService;

@Service // Using service and not @Component because it clearly states the behaviour of this service
@Profile({"default", "map"}) // Annotating the Map Services as default (explicitly) and giving it its own profile name
public class SpecialityMapService extends AbstractMapService<Speciality, Long>  implements SpecialityService {

	@Override
	public Set<Speciality> findAll() {
		return super.findAll();
	}

	@Override
	public Speciality findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Speciality save(Speciality object) {
		return super.save(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Speciality object) {
		super.delete(object);
	}

}
