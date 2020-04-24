package com.lucianaugusto.mypetclinic.services.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lucianaugusto.mypetclinic.model.PetType;

public class PetTypeMapServiceTest {

	PetTypeMapService petTypeMapService;
	final Long petTypeId = 1L;
	
	@BeforeEach
	protected void setUp() throws Exception {
		petTypeMapService = new PetTypeMapService();
		petTypeMapService.save(PetType.builder().id(petTypeId).build());
	}

	@Test
	public void testFindAll() throws Exception {
		Set<PetType> petTypes = petTypeMapService.findAll();
		
		assertEquals(1, petTypes.size());
	}

	@Test
	public void testFindById() throws Exception {
		PetType petType = petTypeMapService.findById(petTypeId);
		
		assertEquals(petTypeId, petType.getId());
	}

	@Test
	public void testSave() throws Exception {
		Long id = 2L;
		PetType newPetType = PetType.builder().id(id).build();
		PetType savedPetType = petTypeMapService.save(newPetType);
		
		assertEquals(id, savedPetType.getId());
	}
	
	@Test
	public void testSaveNoId() throws Exception {
		PetType savedPetType = petTypeMapService.save(PetType.builder().build());
		
		assertNotNull(savedPetType);
		assertNotNull(savedPetType.getId());
	}

	@Test
	public void testDeleteById() throws Exception {
		petTypeMapService.delete(petTypeMapService.findById(petTypeId));
		
		assertEquals(0, petTypeMapService.findAll().size());
	}

	@Test
	public void testDelete() throws Exception {
		petTypeMapService.deleteById(petTypeId);
		
		assertEquals(0, petTypeMapService.findAll().size());
	}

}
