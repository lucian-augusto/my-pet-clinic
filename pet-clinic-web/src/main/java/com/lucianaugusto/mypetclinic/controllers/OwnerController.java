package com.lucianaugusto.mypetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/owners"}) // Pre fixing the class level
@Controller
public class OwnerController {

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String listOwners() {
		return "owners/index";
	}
}
