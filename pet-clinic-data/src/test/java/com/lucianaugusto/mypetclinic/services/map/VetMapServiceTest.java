package com.lucianaugusto.mypetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lucianaugusto.mypetclinic.model.Vet;

public class VetMapServiceTest {

	VetMapService vetMapService;
	
	final Long vetId = 1L;
	
	@BeforeEach
	protected void setUp() throws Exception {
		vetMapService = new VetMapService(new SpecialityMapService());
		vetMapService.save(Vet.builder().id(vetId).build());
	}

	@Test
	public void testFindAll() throws Exception {
		Set<Vet> vets = vetMapService.findAll();
		
		assertEquals(1, vets.size());
	}

	@Test
	public void testFindById() throws Exception {
		Vet vet = vetMapService.findById(vetId);
		
		assertEquals(vetId, vet.getId());
	}

	@Test
	public void testSave() throws Exception {
		Long id = 2L;
		Vet vet2 = Vet.builder().id(id).build();
		Vet savedVet = vetMapService.save(vet2);
		
		assertEquals(id, savedVet.getId());
	}
	
	@Test
	public void testSaveNoId() throws Exception {
		Vet newVet = vetMapService.save(Vet.builder().build());
		
		assertNotNull(newVet);
		assertNotNull(newVet.getId());
	}

	@Test
	public void testDelete() throws Exception {
		vetMapService.delete(vetMapService.findById(vetId));
		
		assertEquals(0, vetMapService.findAll().size());
	}

	@Test
	public void testDeleteById() throws Exception {
		vetMapService.deleteById(vetId);
		
		assertEquals(0, vetMapService.findAll().size());
	}

}
