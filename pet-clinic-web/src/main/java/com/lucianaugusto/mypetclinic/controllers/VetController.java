package com.lucianaugusto.mypetclinic.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lucianaugusto.mypetclinic.model.Vet;
import com.lucianaugusto.mypetclinic.services.VetService;

@Controller
public class VetController {
//	Service
	private final VetService vetService;

//	Constructor
	public VetController(VetService vetService) {
		super();
		this.vetService = vetService;
	}

	@RequestMapping({ "/vets", "/vets/", "/vets/index", "/vets/index.html", "/vets.html" })
	public String listVets(Model model) { //

		model.addAttribute("vets", vetService.findAll());

		return "vets/index"; // Returns the string that corresponds to the index.html file inside the vets
								// folder inside the
		// templates folder.
	}

	@GetMapping("/api/vets")
	public @ResponseBody Set<Vet> getVetJson() {
		return vetService.findAll();
	}
}
