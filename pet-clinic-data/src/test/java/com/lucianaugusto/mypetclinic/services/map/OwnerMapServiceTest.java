package com.lucianaugusto.mypetclinic.services.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lucianaugusto.mypetclinic.model.Owner;

public class OwnerMapServiceTest {

	OwnerMapService ownerMapService;
	
	final Long ownerId = 1L;
	final String lastName = "Doe";
	
	@BeforeEach
	protected void setUp() throws Exception {
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
	}

	@Test
	public void testFindAll() throws Exception {
		Set<Owner> ownerSet = ownerMapService.findAll();
		assertEquals(1, ownerSet.size());
	}

	@Test
	public void testFindById() throws Exception {
		Owner owner = ownerMapService.findById(ownerId);
		assertEquals(ownerId, owner.getId());
	}

	@Test
	public void testSave() throws Exception {
		Long id = 2L;
		Owner owner2 = Owner.builder().id(id).build();
		Owner savedOwner = ownerMapService.save(owner2);
		
		assertEquals(id, savedOwner.getId());
	}

	@Test
	public void testSaveNoId() throws Exception {
		Owner savedOwner = ownerMapService.save(Owner.builder().build());
		
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}
	

	@Test
	public void testDelete() throws Exception {
		ownerMapService.delete(ownerMapService.findById(ownerId));
		
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	public void testDeleteById() throws Exception {
		ownerMapService.deleteById(ownerId);
		
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	public void testFindByLastName() throws Exception {
		Owner owner = ownerMapService.findByLastName(lastName);
		
		assertNotNull(owner);
		assertEquals(ownerId, owner.getId());
		
	}


}
