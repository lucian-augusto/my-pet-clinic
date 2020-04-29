package com.lucianaugusto.mypetclinic.controllers;

import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {
	
	@Mock
	OwnerService ownerService;
	
	@InjectMocks
	OwnerController ownerController;
	
	Set<Owner> owners;
	
	MockMvc mockMvc;

	@BeforeEach
	protected void setUp() throws Exception {
		owners = new HashSet<Owner>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());
		
		mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
	}

	@Test
	public void testListOwners() throws Exception {
		when(ownerService.findAll()).thenReturn(owners);
		
		mockMvc.perform(get("/owners"))
			.andExpect(status().isOk()) // Expects a returned status of 200
			.andExpect(view().name("owners/index"))
			.andExpect(model().attribute("owners", Matchers.hasSize(2)));
	}
	
	@Test
	public void testListOwnersByIndex() throws Exception {
		when(ownerService.findAll()).thenReturn(owners);
		
		mockMvc.perform(get("/owners/index"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/index"))
			.andExpect(model().attribute("owners", Matchers.hasSize(2)));
	}

	@Test
	public void testFindOwners() throws Exception {
		mockMvc.perform(get("/owners/find"))
			.andExpect(status().isOk())
			.andExpect(view().name("notImplemented"));
		
		verifyZeroInteractions(ownerService);
	}

}
