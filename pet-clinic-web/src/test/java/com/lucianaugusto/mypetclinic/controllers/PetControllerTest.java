package com.lucianaugusto.mypetclinic.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

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
import com.lucianaugusto.mypetclinic.model.Pet;
import com.lucianaugusto.mypetclinic.model.PetType;
import com.lucianaugusto.mypetclinic.services.OwnerService;
import com.lucianaugusto.mypetclinic.services.PetService;
import com.lucianaugusto.mypetclinic.services.PetTypeService;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {
	
	@BeforeEach
	public void setUp() {
		owner = Owner.builder().id(7L).build();
		
		petTypes = new HashSet<>();
		petTypes.add(PetType.builder().id(1L).name("Dog").build());
		petTypes.add(PetType.builder().id(2L).name("Cat").build());
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testCreatePetForm() throws Exception {
		when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		mockMvc.perform(get("/owners/7/pets/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owner", "pet"))
			.andExpect(view().name("pets/createOrUpdatePetForm"));
		
		verifyZeroInteractions(petService);
	}

	@Test
	public void testCreatePet() throws Exception {
		when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		mockMvc.perform(post("/owners/7/pets/new"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/7"))
			.andExpect(model().attributeExists("owner", "pet"));
		
		verify(petService).save(ArgumentMatchers.any(Pet.class));
	}
	
	@Test
	public void testUpdatePetForm() throws Exception {
		when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		when(petService.findById(ArgumentMatchers.anyLong())).thenReturn(Pet.builder().id(2L).build());
		
		mockMvc.perform(get("/owners/7/pets/2/edit"))
			.andExpect(status().isOk())
			.andExpect(view().name("pets/createOrUpdatePetForm"))
			.andExpect(model().attributeExists("owner", "pet"));
	}
	
	@Test
	public void testUpdatePet() throws Exception {
		when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		Pet pet = Pet.builder().id(2L).build();
		
		mockMvc.perform(post("/owners/7/pets/2/edit"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/7"))
			.andExpect(model().attributeExists("owner", "pet"));
		
		verify(petService).save(ArgumentMatchers.any(Pet.class));
	}
	
	@Mock
	PetService petService;
	
	@Mock
	OwnerService ownerService;
	
	@Mock
	PetTypeService petTypeService;
	
	@InjectMocks
	PetController controller;
	
	MockMvc mockMvc;
	
	Owner owner;
	
	Set<PetType> petTypes;
	
}
