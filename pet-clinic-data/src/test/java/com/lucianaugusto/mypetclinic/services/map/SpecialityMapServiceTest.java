package com.lucianaugusto.mypetclinic.services.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lucianaugusto.mypetclinic.model.Speciality;

public class SpecialityMapServiceTest {

	SpecialityMapService specialityMapService;
	final Long specialityId = 1L;
	
	@BeforeEach
	protected void setUp() throws Exception {
		specialityMapService = new SpecialityMapService();
		specialityMapService.save(Speciality.builder().id(specialityId).build());
	}

	@Test
	public void testFindAll() throws Exception {
		Set<Speciality> specialities = specialityMapService.findAll();
		
		assertEquals(1, specialities.size());
	}

	@Test
	public void testFindById() throws Exception {
		Speciality speciality = specialityMapService.findById(specialityId);
		
		assertEquals(specialityId, speciality.getId());
	}

	@Test
	public void testSave() throws Exception {
		Long id = 2L;
		Speciality newSpeciality = Speciality.builder().id(id).build();
		Speciality savedSpeciality = specialityMapService.save(newSpeciality);
		
		assertEquals(id, savedSpeciality.getId());
	}
	
	@Test
	public void testSaveNoId() throws Exception {
		Speciality savedSpeciality = specialityMapService.save(Speciality.builder().build());
		
		assertNotNull(savedSpeciality);
		assertNotNull(savedSpeciality.getId());
	}

	@Test
	public void testDeleteById() throws Exception {
		specialityMapService.delete(specialityMapService.findById(specialityId));
		
		assertEquals(0, specialityMapService.findAll().size());
	}

	@Test
	public void testDelete() throws Exception {
		specialityMapService.deleteById(specialityId);
		
		assertEquals(0, specialityMapService.findAll().size());	}

}
