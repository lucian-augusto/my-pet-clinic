package com.lucianaugusto.mypetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.model.Pet;
import com.lucianaugusto.mypetclinic.model.Visit;

public class VisitMapServiceTest {
	
	VisitMapService visitMapService;
	
	final Long visitId = 1L;
	Owner owner = Owner.builder().id(visitId).build();
	Pet pet = Pet.builder().owner(owner).id(visitId).build();
	
	@BeforeEach
	protected void setUp() throws Exception {
		visitMapService = new VisitMapService();
		visitMapService.save(Visit.builder().id(visitId).pet(pet).build());
	}

	@Test
	public void testFindAll() throws Exception {
		Set<Visit> visits = visitMapService.findAll();
		
		assertEquals(1, visits.size());
	}

	@Test
	public void testFindById() throws Exception {
		Visit visit = visitMapService.findById(visitId);
		
		assertEquals(visitId, visit.getId());
	}

	@Test
	public void testSave() throws Exception {
		Long id = 2L;
		Visit newVisit = Visit.builder().id(id).pet(pet).build();
		Visit savedVisit = visitMapService.save(newVisit);
		
		assertEquals(id, savedVisit.getId());
	}
	
	@Test
	public void testSaveNoId() throws Exception {
		Visit savedVisit = visitMapService.save(Visit.builder().pet(pet).build());
		
		assertNotNull(savedVisit);
		assertNotNull(savedVisit.getId());
	}

	@Test
	public void testDeleteById() throws Exception {
		visitMapService.delete(visitMapService.findById(visitId));
		
		assertEquals(0, visitMapService.findAll().size());
	}

	@Test
	public void testDelete() throws Exception {
		visitMapService.deleteById(visitId);;
		
		assertEquals(0, visitMapService.findAll().size());
	}

}
