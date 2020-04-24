package com.lucianaugusto.mypetclinic.services.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lucianaugusto.mypetclinic.model.Pet;

public class PetMapServiceTest {
	
	PetMapService petMapService;
	final Long petId = 1L;

	@BeforeEach
	protected void setUp() throws Exception {
		petMapService = new PetMapService();
		petMapService.save(Pet.builder().id(petId).build());
	}

	@Test
	public void testFindAll() throws Exception {
		Set<Pet> pets = petMapService.findAll();
		
		assertEquals(1, pets.size());
	}

	@Test
	public void testFindById() throws Exception {
		Pet pet = petMapService.findById(petId);
		
		assertEquals(petId, pet.getId());
	}

	@Test
	public void testSave() throws Exception {
		Long id = 2L;
		Pet pet2 = Pet.builder().id(id).build();
		Pet savedPet = petMapService.save(pet2);
		
		assertEquals(id, savedPet.getId());
	}
	
	@Test
	public void testSaveNoId() throws Exception {
		Pet savedPet = petMapService.save(Pet.builder().build());
		
		assertNotNull(savedPet);
		assertNotNull(savedPet.getId());
	}

	@Test
	public void testDelete() throws Exception {
		petMapService.delete(petMapService.findById(petId));
		
		assertEquals(0, petMapService.findAll().size());
	}

	@Test
	public void testDeleteById() throws Exception {
		petMapService.deleteById(petId);
		assertEquals(0, petMapService.findAll().size());
	}

}
