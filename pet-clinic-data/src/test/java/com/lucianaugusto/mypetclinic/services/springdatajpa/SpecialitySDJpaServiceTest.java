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

import com.lucianaugusto.mypetclinic.model.Speciality;
import com.lucianaugusto.mypetclinic.repositories.SpecialityRepository;

@ExtendWith(MockitoExtension.class)
public class SpecialitySDJpaServiceTest {
	
	final Long specialityId = 1L;
	Speciality speciality;
	
	@Mock
	SpecialityRepository specialityRepository;
	
	@InjectMocks
	SpecialitySDJpaService specialityService;

	@BeforeEach
	protected void setUp() throws Exception {
		speciality = Speciality.builder().id(specialityId).build();
	}

	@Test
	public void testFindAll() throws Exception {
		Set<Speciality> specialities = new HashSet<Speciality>();
		specialities.add(speciality);
		specialities.add(Speciality.builder().id(2L).build());
		
		when(specialityRepository.findAll()).thenReturn(specialities);
		
		Set<Speciality> foundSpecialities = specialityService.findAll();
		
		assertNotNull(foundSpecialities);
		assertEquals(2, foundSpecialities.size());
	}

	@Test
	public void testFindById() throws Exception {
		when(specialityRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(speciality));
		
		Speciality foundSpeciality = specialityService.findById(specialityId);
		
		assertNotNull(foundSpeciality);

	}
	
	@Test
	public void testFindByIdNotFound() throws Exception {
		when(specialityRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		Speciality foundSpeciality = specialityService.findById(specialityId);
		
		assertNull(foundSpeciality);

	}

	@Test
	public void testSave() throws Exception {
		when(specialityRepository.save(ArgumentMatchers.any())).thenReturn(speciality);
		
		Speciality savedSpeciality = specialityService.save(Speciality.builder().id(specialityId).build());
		
		assertNotNull(savedSpeciality);
		verify(specialityRepository).save(ArgumentMatchers.any());
	}

	@Test
	public void testDelete() throws Exception {
		specialityService.delete(speciality);
		
		verify(specialityRepository).delete(ArgumentMatchers.any());
	}

	@Test
	public void testDeleteById() throws Exception {
		specialityService.deleteById(specialityId);
		
		verify(specialityRepository).deleteById(ArgumentMatchers.anyLong());
	}

}
