package com.lucianaugusto.mypetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

	@RequestMapping({"/vets", "/vets/index", "/vets/index.html"})
	public String listVets() { // 
		return "vets/index"; // Returns the string that corresponds to the index.html file inside the vets folder inside the
		// templates folder.
	}
}
