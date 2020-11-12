package com.lucianaugusto.mypetclinic.formatters;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.lucianaugusto.mypetclinic.model.PetType;
import com.lucianaugusto.mypetclinic.services.PetTypeService;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

	private final PetTypeService petTypeService;

	public PetTypeFormatter(PetTypeService petTypeService) {
		super();
		this.petTypeService = petTypeService;
	}

	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}

	@Override
	public PetType parse(String text, Locale locale) throws ParseException {
		 Collection<PetType> petTypes = petTypeService.findAll();
		 
		 for (PetType petType : petTypes) {
			 if (petType.getName().equals(text)) {
				return petType;
			}
		 }
		throw new ParseException("Type " + text + " not found ", 0);
	}
	
	
}
