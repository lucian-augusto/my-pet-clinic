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

import com.lucianaugusto.mypetclinic.model.Vet;
import com.lucianaugusto.mypetclinic.repositories.VetRepository;

@ExtendWith(MockitoExtension.class)
public class VetSDJpaServiceTest {
	
	final Long vetId = 1L;
	Vet vet;
	
	@Mock
	VetRepository vetRepository;
	
	@InjectMocks
	VetSDJpaService vetService;

	@BeforeEach
	protected void setUp() throws Exception {
		vet = Vet.builder().id(vetId).build();
	}

	@Test
	public void testFindAll() throws Exception {
		Set<Vet> vets = new HashSet<Vet>();
		vets.add(vet);
		vets.add(Vet.builder().id(2L).build());
		
		when(vetRepository.findAll()).thenReturn(vets);
		
		Set<Vet> foundVets = vetService.findAll();
		
		assertNotNull(foundVets);
		assertEquals(2, foundVets.size());
	}

	@Test
	public void testFindById() throws Exception {
		when(vetRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(vet));
		
		Vet foundVet = vetService.findById(vetId);
		
		assertNotNull(foundVet);
	}
	
	@Test
	public void testFindByIdNotFound() throws Exception {
		when(vetRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		Vet foundVet = vetService.findById(vetId);
		
		assertNull(foundVet);
	}

	@Test
	public void testSave() throws Exception {
		when(vetRepository.save(ArgumentMatchers.any())).thenReturn(vet);
		
		Vet savedVet = vetService.save(vet);
		
		assertNotNull(savedVet);
		verify(vetRepository).save(ArgumentMatchers.any());
	}

	@Test
	public void testDelete() throws Exception {
		vetService.delete(vet);
		
		verify(vetRepository).delete(ArgumentMatchers.any());
	}

	@Test
	public void testDeleteById() throws Exception {
		vetService.deleteById(vetId);
		
		verify(vetRepository).deleteById(ArgumentMatchers.anyLong());
	}

}
