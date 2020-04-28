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

import com.lucianaugusto.mypetclinic.model.Visit;
import com.lucianaugusto.mypetclinic.repositories.VisitRepository;

@ExtendWith(MockitoExtension.class)
public class VisitSDJpaServiceTest {
	
	final Long visitId = 1L;
	Visit visit;
	
	@Mock
	VisitRepository visitRepository;
	
	@InjectMocks
	VisitSDJpaService visitService;

	@BeforeEach
	protected void setUp() throws Exception {
		visit = Visit.builder().id(visitId).build();
	}

	@Test
	public void testFindAll() throws Exception {
		Set<Visit> visits = new HashSet<Visit>();
		visits.add(visit);
		visits.add(Visit.builder().id(2L).build());
		
		when(visitRepository.findAll()).thenReturn(visits);
		
		Set<Visit> foundVisits = visitService.findAll();
		
		assertNotNull(foundVisits);
		assertEquals(2, foundVisits.size());
	}

	@Test
	public void testFindById() throws Exception {
		when(visitRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(visit));
		
		Visit foundVisit = visitService.findById(visitId);
		
		assertNotNull(foundVisit);
	}
	
	@Test
	public void testFindByIdNotFound() throws Exception {
		when(visitRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		Visit foundVisit = visitService.findById(visitId);
		
		assertNull(foundVisit);
	}

	@Test
	public void testSave() throws Exception {
		when(visitRepository.save(ArgumentMatchers.any())).thenReturn(visit);
		
		Visit savedVisit = visitService.save(visit);
		
		assertNotNull(savedVisit);
		verify(visitRepository).save(ArgumentMatchers.any());
	}

	@Test
	public void testDelete() throws Exception {
		visitService.delete(visit);
		
		verify(visitRepository).delete(ArgumentMatchers.any());
	}

	@Test
	public void testDeleteById() throws Exception {
		visitService.deleteById(visitId);
		
		verify(visitRepository).deleteById(ArgumentMatchers.anyLong());
	}

}
