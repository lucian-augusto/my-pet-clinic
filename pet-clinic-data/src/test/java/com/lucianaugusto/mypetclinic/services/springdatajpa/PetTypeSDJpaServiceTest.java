package com.lucianaugusto.mypetclinic.services.springdatajpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import com.lucianaugusto.mypetclinic.model.PetType;
import com.lucianaugusto.mypetclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
public class PetTypeSDJpaServiceTest {

	final Long petTypeId = 1L;
	PetType type;
	
	@Mock
	PetTypeRepository petTypeRepository;
	
	@InjectMocks
	PetTypeSDJpaService petTypeService;
	
	@BeforeEach
	protected void setUp() throws Exception {
		type = PetType.builder().id(petTypeId).build();
	}

	@Test
	public void testFindAll() throws Exception {
		Set<PetType> types = new HashSet<>();
		types.add(type);
		types.add(PetType.builder().id(1L).build());
		
		when(petTypeRepository.findAll()).thenReturn(types);
		
		Set<PetType> foundTypes = petTypeService.findAll();
		
		assertNotNull(foundTypes);
		assertEquals(2, foundTypes.size());
	}

	@Test
	public void testFindById() throws Exception {
		when(petTypeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(type));
		
		PetType foundType = petTypeService.findById(petTypeId);
		
		assertNotNull(foundType);
	}
	
	@Test
	public void testFindByIdNotFound() throws Exception {
		when(petTypeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		PetType foundType = petTypeService.findById(petTypeId);
		
		assertNull(foundType);
	}

	@Test
	public void testSave() throws Exception {
		when(petTypeRepository.save(ArgumentMatchers.any())).thenReturn(type);
		
		PetType savedType = petTypeService.save(PetType.builder().id(petTypeId).build());
		
		assertNotNull(savedType);
		verify(petTypeRepository).save(ArgumentMatchers.any());
	}

	@Test
	public void testDelete() throws Exception {
		petTypeService.delete(type);
		
		verify(petTypeRepository).delete(ArgumentMatchers.any());
	}

	@Test
	public void testDeleteById() throws Exception {
		petTypeService.deleteById(petTypeId);
		
		verify(petTypeRepository).deleteById(ArgumentMatchers.anyLong());
	}

}
