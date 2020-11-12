package com.lucianaugusto.mypetclinic.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
import com.lucianaugusto.mypetclinic.model.PetType;
import com.lucianaugusto.mypetclinic.services.OwnerService;
import com.lucianaugusto.mypetclinic.services.PetService;
import com.lucianaugusto.mypetclinic.services.PetTypeService;

@Controller
@RequestMapping({"/owners/{ownerId}"})
public class PetController {

	
	private final PetService petService;
	private final OwnerService ownerService;
	private final PetTypeService petTypeService;
	
	public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
		super();
		this.petService = petService;
		this.ownerService = ownerService;
		this.petTypeService = petTypeService;
	}
	
	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return petTypeService.findAll();
	}
	
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
		return ownerService.findById(ownerId);
	}
	
	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/pets/new")
	public String createPetForm(Owner owner, Model model) {
		Pet newPet = new Pet();
		owner.getPets().add(newPet);
		model.addAttribute("pet", newPet);
		
		return "pets/createOrUpdatePetForm";
	}
	
	@PostMapping("/pets/new")
	public String createPet(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
		if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}
		
		owner.getPets().add(pet);
		
		if (result.hasErrors()) {
			return "pets/createOrUpdatePetForm";
		}
		
		petService.save(pet);
		
		return "redirect:/owners/" + owner.getId();
	}
	
	@GetMapping("/pets/{petId}/edit")
	public String updatePetForm(@PathVariable Long petId, Model model) {
		model.addAttribute("pet", petService.findById(petId));
		
		return "pets/createOrUpdatePetForm";
	}
	
	@PostMapping("/pets/{petId}/edit")
	public String updatePet(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
		if (result.hasErrors()) {
			pet.setOwner(owner);
			return "pets/createOrUpdatePetForm";
		}
		owner.getPets().add(pet);
		petService.save(pet);
		
		return "redirect:/owners/" + owner.getId();
	}
}
