package com.lucianaugusto.mypetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lucianaugusto.mypetclinic.services.OwnerService;

@RequestMapping({"/owners"}) // Prefixing the class level
@Controller
public class OwnerController {
	
//	Getting a handle of the Owner service
	private final OwnerService ownerService; // Object declared as final so it can never be changed.

//	Constructor(-based Injection - Best Practice)
	public OwnerController(OwnerService ownerService) {
	super();
	this.ownerService = ownerService;
}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String listOwners(Model model) {
		
		model.addAttribute("owners", ownerService.findAll()); // When called by Spring MVC, the MVC will inject the model
		// into it, adding an attribute called "owner" which will be all the owners that will be returned from the service's
		// findAll() method.
		return "owners/index";
	}
}
