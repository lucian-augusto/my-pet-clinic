package com.lucianaugusto.mypetclinic.controllers;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.model.Pet;
import com.lucianaugusto.mypetclinic.model.PetType;
import com.lucianaugusto.mypetclinic.services.PetService;
import com.lucianaugusto.mypetclinic.services.VisitService;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {

	@Mock
	private PetService petService;

	@Mock
	private VisitService visitService;

	@InjectMocks
	private VisitController controller;

	private MockMvc mockMvc;

	private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
	private final Map<String, String> uriVariables = new HashMap<>();
	private URI visitsUri;

	@BeforeEach
	public void setUp() {
		Long petId = 1L;
		Long ownerId = 1L;
		Owner owner = Owner.builder().id(ownerId).firstName("John").lastName("Doe").build();
		Pet pet = Pet.builder().id(petId).birthDate(LocalDate.of(2020, 11, 18)).name("Cacau").visits(new HashSet<>())
				.owner(owner).petType(PetType.builder().name("Dog").build()).build();

		when(petService.findById(petId)).thenReturn(pet);

		uriVariables.clear();
		uriVariables.put("ownerId", ownerId.toString());
		uriVariables.put("petId", petId.toString());
		visitsUri = visitsUriTemplate.expand(uriVariables);

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testCreateVisitForm() throws Exception {
		mockMvc.perform(get(visitsUri)).andExpect(status().isOk())
				.andExpect(view().name("pets/createOrUpdateVisitForm")).andExpect(model().attributeExists("pet"));

		verifyNoInteractions(visitService);
	}

	@Test
	public void testCreateVisit() throws Exception {
		mockMvc.perform(post(visitsUri).contentType(MediaType.APPLICATION_FORM_URLENCODED).param("date", "2018-11-11")
				.param("description", "description")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1")).andExpect(model().attributeExists("visit"));
	}
}
