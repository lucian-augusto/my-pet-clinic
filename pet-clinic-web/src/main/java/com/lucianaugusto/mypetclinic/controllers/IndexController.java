package com.lucianaugusto.mypetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController { // Controller that handles the requests made by the user
	
	@RequestMapping({"", "/", "index", "index.html"})
	public String index() { // Methods that handles the request listed inside the @RequestMapping annotation and returns 
		// the index.html file using thymeleaf
		return "index";
	}

}
