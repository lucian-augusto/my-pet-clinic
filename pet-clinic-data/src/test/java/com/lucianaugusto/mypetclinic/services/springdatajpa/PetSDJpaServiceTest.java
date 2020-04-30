package com.lucianaugusto.mypetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lucianaugusto.mypetclinic.model.Pet;
import com.lucianaugusto.mypetclinic.repositories.PetRepository;

@ExtendWith(MockitoExtension.class)
public class PetSDJpaServiceTest {
	
	final Long petId = 1L;
	@Mock
	PetRepository petRepository;
	
	@InjectMocks
	PetSDJpaService petService;
	
	Pet pet;

	@BeforeEach
	protected void setUp() throws Exception {
		pet = Pet.builder().id(petId).build();
	}

	@Test
	public void testFindAll() throws Exception {
		Set<Pet> pets = new HashSet<>();
		pets.add(pet);
		pets.add(Pet.builder().id(2L).build());
		
		when(petRepository.findAll()).thenReturn(pets);
		
		Set<Pet> foundPets = petService.findAll();
		
		assertNotNull(foundPets);
		assertEquals(2, foundPets.size());
	}

	@Test
	public void testFindById() throws Exception {
		when(petRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(pet));
		
		Pet foundPet = petService.findById(petId);
		
		assertNotNull(foundPet);
	}
	
	@Test
	public void testFindByIdNotFound() throws Exception {
		when(petRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		Pet foundPet = petService.findById(petId);
		
		assertNull(foundPet);
	}

	@Test
	public void testSave() throws Exception {
		when(petRepository.save(ArgumentMatchers.any())).thenReturn(pet);
		
		Pet savedPet = petService.save(Pet.builder().id(petId).build());
		
		assertNotNull(savedPet);
		verify(petRepository).save(ArgumentMatchers.any());
	}

	@Test
	public void testDelete() throws Exception {
		petService.delete(pet);
		
		verify(petRepository).delete(ArgumentMatchers.any());
	}

	@Test
	public void testDeleteById() throws Exception {
		petService.deleteById(petId);
		
		verify(petRepository).deleteById(ArgumentMatchers.anyLong());
	}

}
