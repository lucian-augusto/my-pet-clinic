package com.lucianaugusto.mypetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.repositories.OwnerRepository;
import com.lucianaugusto.mypetclinic.repositories.PetRepository;
import com.lucianaugusto.mypetclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
public class OwnerSDJpaServiceTest {

	private final String LAST_NAME = "Doe";
	private final Long ownerId = 1L;
	Owner owner;
	
	@Mock
	OwnerRepository ownerRepository;
	
	@Mock
	PetRepository petRepository;
	
	@Mock
	PetTypeRepository petTypeRepository;
	
	@InjectMocks
	OwnerSDJpaService ownerService;
	
	@BeforeEach
	protected void setUp() throws Exception {
		owner = Owner.builder().id(1L).lastName(LAST_NAME).build();
	}

	@Test
	public void testFindAll() throws Exception {
		Set<Owner> ownerSet = new HashSet<>();
		ownerSet.add(owner);
		ownerSet.add(Owner.builder().id(2L).build());
		
		when(ownerRepository.findAll()).thenReturn(ownerSet);
		
		Set<Owner> foundOwners = ownerService.findAll();
		
		assertNotNull(foundOwners);
		assertEquals(2, foundOwners.size());
	}

	@Test
	public void testFindById() throws Exception {
		when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(owner));
		
		Owner foundOwner = ownerService.findById(ownerId);
		
		assertNotNull(foundOwner);
	}
	
	@Test
	public void testFindByIdNotFound() throws Exception {
		when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		Owner foundOwner = ownerService.findById(ownerId);
		
		assertNull(foundOwner);
	}

	@Test
	public void testSave() throws Exception {
		when(ownerRepository.save(ArgumentMatchers.any())).thenReturn(owner);
		
		Owner savedOwner = ownerService.save(Owner.builder().id(ownerId).build());
		
		assertNotNull(savedOwner);
		verify(ownerRepository).save(ArgumentMatchers.any());
	}

	@Test
	public void testDelete() throws Exception {
		ownerService.delete(owner);
		
		// default is i time
		verify(ownerRepository, times(1)).delete(ArgumentMatchers.any());
	}

	@Test
	public void testDeleteById() throws Exception {
		ownerService.deleteById(ownerId);
		
		verify(ownerRepository).deleteById(ArgumentMatchers.anyLong());
	}

	@Test
	public void testFindByLastName() throws Exception {		
		when(ownerRepository.findByLastName(ArgumentMatchers.any())).thenReturn(owner);
		
		Owner doe = ownerService.findByLastName(LAST_NAME);
		
		assertEquals(LAST_NAME, doe.getLastName());
		
		verify(ownerRepository).findByLastName(ArgumentMatchers.any());
	}
	
	@Test
	public void testFindAllByLastName() throws Exception {
		Owner owner2 = Owner.builder().id(7L).lastName(LAST_NAME).build();
		List<Owner> owners = new LinkedList<>();
		owners.add(owner);
		owners.add(owner2);
		when(ownerRepository.findAllByLastName(ArgumentMatchers.anyString())).thenReturn(owners);
		
		List<Owner> foundOwners = ownerService.findAllByLastName(LAST_NAME);
		
		assertEquals(2, foundOwners.size());
		assertEquals(LAST_NAME, foundOwners.get(0).getLastName());
		assertEquals(LAST_NAME, foundOwners.get(1).getLastName());
		
		verify(ownerRepository).findAllByLastName(ArgumentMatchers.anyString());
	}

}
