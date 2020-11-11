package com.lucianaugusto.mypetclinic.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.services.OwnerService;

@RequestMapping({"/owners"}) // Prefixing the class level
@Controller
public class OwnerController {
	
//	Getting a handle of the Owner service
	private final OwnerService ownerService; // Object declared as final so it can never be changed.

//	Constructor(Constructor-based Injection - Best Practice)
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

//	@RequestMapping({"", "/", "/index", "/index.html"})
//	public String listOwners(Model model) {
//		
//		model.addAttribute("owners", ownerService.findAll()); // When called by Spring MVC, the MVC will inject the model
//		// into it, adding an attribute called "owner" which will be all the owners that will be returned from the service's
//		// findAll() method.
//		return "owners/index";
//	}
	
//	Setting up a placeholder for the "find owners" page fixing the broken link
	@RequestMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";
	}
	
	@GetMapping
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		// allow parameterless GET requests for /owners to return all records
		if (owner.getLastName() == null) {
			owner.setLastName(""); // Broadest possible search 
		}
		
		// find owners by last name
		List<Owner> owners = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
		
		if (owners.isEmpty()) { // no owners found
			result.rejectValue("lastName", "not found", "not found");
			
			return "owners/findOwners"; 
		}
		
		if (owners.size() == 1) { // 1 owner found
			owner = owners.get(0);
			
			return "redirect:/owners/" + owner.getId();
		}
		
		// more than 1 owners found
		model.addAttribute("foundOwners", owners);
		return "owners/ownersList";
	}
	
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable Long ownerId) {
		ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
		modelAndView.addObject(ownerService.findById(ownerId));
		
		return modelAndView;
	}
	
	@GetMapping("/new")
	public String createNewOwnerForm(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		
		return "owners/createOrUpdateOwnerForm";
	}
	
	@PostMapping("/new")
	public String createNewOwner(@Valid Owner owner, BindingResult result) {
		if (result.hasErrors()) {
			return "owners/createOrUpdateOwnerForm";
		}
		Owner savedOwner = ownerService.save(owner);
		return "redirect:/owners/" + savedOwner.getId();
	}
	
	@GetMapping("/{ownerId}/edit")
	public String updateOwnerForm(@PathVariable Long ownerId, Model model) {
		model.addAttribute(ownerService.findById(ownerId));
		
		return "owners/createOrUpdateOwnerForm";
	}
	
	@PostMapping("/{ownerId}/edit")
	public String updateOwner(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
		if (result.hasErrors()) {
			return "owners/createOrUpdateOwnerForm";
		}
		owner.setId(ownerId);
		Owner savedOwner = ownerService.save(owner);
		return "redirect:/owners/" + savedOwner.getId();
	}
}
