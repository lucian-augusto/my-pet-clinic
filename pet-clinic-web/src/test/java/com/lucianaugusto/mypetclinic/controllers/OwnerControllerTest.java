package com.lucianaugusto.mypetclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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
	public void testFindOwners() throws Exception {
		mockMvc.perform(get("/owners/find"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/findOwners"))
			.andExpect(model().attributeExists("owner"));
		
		verifyZeroInteractions(ownerService);
	}
	
	@Test
	public void testProcessFormReturnMany() throws Exception {
		when(ownerService.findAllByLastNameLike(ArgumentMatchers.anyString())).thenReturn(Arrays.asList(Owner.builder().id(7L).build(), Owner.builder().id(123L).build()));
		
		mockMvc.perform(get("/owners"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/ownersList"))
			.andExpect(model().attribute("foundOwners", Matchers.hasSize(2)));
	}
	
	@Test
	public void testProcessFormReturnOne() throws Exception {
		when(ownerService.findAllByLastNameLike(ArgumentMatchers.anyString())).thenReturn(Arrays.asList(Owner.builder().id(7L).build()));
		
		mockMvc.perform(get("/owners"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/7"));
	}
	
	@Test
	public void testShowOwner() throws Exception {
		when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(Owner.builder().id(7L).build());
		
		mockMvc.perform(get("/owners/7"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/ownerDetails"))
			.andExpect(model().attribute("owner", Matchers.hasProperty("id", is(7L))));
	}
	
	@Test
	public void testCreateNewOwnerForm() throws Exception {
		mockMvc.perform(get("/owners/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(model().attributeExists("owner"));
		
		verifyZeroInteractions(ownerService );
	}
	
	@Test
	public void testCreateNewOwner() throws Exception {
		when(ownerService.save(ArgumentMatchers.any(Owner.class))).thenReturn(Owner.builder().id(7L).build());
		
		mockMvc.perform(post("/owners/new"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/7"))
			.andExpect(model().attributeExists("owner"));
		
		verify(ownerService).save(ArgumentMatchers.any(Owner.class));
	}
	
	@Test
	public void testUpdateOwnerForm() throws Exception {
		when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(Owner.builder().id(7L).build());
		
		mockMvc.perform(get("/owners/7/edit"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(model().attributeExists("owner"));
		
		verify(ownerService).findById(7L);
	}
	
	@Test
	public void testUpdateOwner() throws Exception {
		Owner owner = Owner.builder().id(7L).build();
		when(ownerService.save(ArgumentMatchers.any(Owner.class))).thenReturn(owner);
		
		mockMvc.perform(post("/owners/7/edit"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/7"))
			.andExpect(model().attribute("owner", Matchers.hasProperty("id", is(7L))));
		
		verify(ownerService).save(ArgumentMatchers.any(Owner.class));
	}

}
