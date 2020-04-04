package com.lucianaugusto.mypetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lucianaugusto.mypetclinic.services.VetService;

@RequestMapping({"/vets"})
@Controller
public class VetController {
//	Service
	private final VetService vetService;
	
//	Constructor
	public VetController(VetService vetService) {
		super();
		this.vetService = vetService;
	}

	@RequestMapping({"", "/", "/index", "/index.html", ".html"})
	public String listVets(Model model) { // 
		
		model.addAttribute("vets", vetService.findAll());
		
		return "vets/index"; // Returns the string that corresponds to the index.html file inside the vets folder inside the
		// templates folder.
	}
}
