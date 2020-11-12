package com.lucianaugusto.mypetclinic.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.model.Pet;
import com.lucianaugusto.mypetclinic.model.Visit;
import com.lucianaugusto.mypetclinic.services.PetService;
import com.lucianaugusto.mypetclinic.services.VisitService;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}")
public class VisitController {

	private final VisitService visitService;
	private final PetService petService;
	
	public VisitController(VisitService visitService, PetService petService) {
		super();
		this.visitService = visitService;
		this.petService = petService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Model model) {
		Pet pet = petService.findById(petId);
		model.addAttribute("pet", pet);
		Visit visit = new Visit();
		pet.getVisits().add(visit);
		visit.setPet(pet);
		return visit;
	}
	
	@GetMapping("/visits/new")
	public String createVisitForm(@PathVariable Long petId, Model model) {
		return "pets/createOrUpdateVisitForm";
	}
	
	@PostMapping("/visits/new")
	public String createVisit(@Valid Visit visit, BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}
		visitService.save(visit);
		
		return "redirect:/owners/" + visit.getPet().getOwner().getId();
	}
}
