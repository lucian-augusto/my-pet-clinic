package com.lucianaugusto.mypetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.model.Pet;
import com.lucianaugusto.mypetclinic.model.PetType;
import com.lucianaugusto.mypetclinic.model.Speciality;
import com.lucianaugusto.mypetclinic.model.Vet;
import com.lucianaugusto.mypetclinic.services.OwnerService;
import com.lucianaugusto.mypetclinic.services.PetTypeService;
import com.lucianaugusto.mypetclinic.services.SpecialityService;
import com.lucianaugusto.mypetclinic.services.VetService;

@Component // By annotating this as a component, this becomes a spring bean and gets registered into the spring context
public class DataLoader implements CommandLineRunner {
	
	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialityService specialityService;

	public DataLoader(OwnerService ownerService, VetService vetService,
			PetTypeService petTypeService, SpecialityService specialityService) { // Takes any implementations of both interfaces and
		// bring them into the context by using Spring to inject the dependencies. Constructor-based Dependency Injection,
		// doesn't require the @Autowired annotation.
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		int count = petTypeService.findAll().size();
		
		if (count == 0) { 
			loadData();
		}
		
	}

	private void loadData() {
		//		Loading Pet Types
				PetType dog = new PetType();
				dog.setName("Dog");
				PetType saveDogPetType = petTypeService.save(dog);
				
				PetType cat = new PetType();
				cat.setName("Cat");
				PetType saveCaPetType = petTypeService.save(cat);
				
				System.out.println("Loaded PetTypes...");
				
		//		Loading Specialities data
				Speciality radiology = new Speciality();
				radiology.setDescription("Radiology");
				Speciality savedRadiology = specialityService.save(radiology);
				
				Speciality surgery = new Speciality();
				surgery.setDescription("Surgery");
				Speciality savedSurgery = specialityService.save(surgery);
				
				Speciality dentisrty = new Speciality();
				dentisrty.setDescription("Dentistry");
				Speciality savedDentistry = specialityService.save(dentisrty);
				
		//		Owner Data Loader
				Owner owner1 = new Owner();
				owner1.setFirstName("Michael");
				owner1.setLastName("Weston");
				owner1.setAddress("111 Pandosy ");
				owner1.setCity("Kelowna");
				owner1.setTelephone("+1 (123) 123 1234");
				
				Pet mikesPet = new Pet();
				mikesPet.setPetType(saveDogPetType);
				mikesPet.setOwner(owner1);
				mikesPet.setBirthDate(LocalDate.now());
				mikesPet.setName("Cacau");
				owner1.getPets().add(mikesPet);
				
				ownerService.save(owner1);
				
				Owner owner2 = new Owner();
				owner2.setFirstName("Fiona");
				owner2.setLastName("Glenanne");
				owner2.setAddress("111 Pandosy ");
				owner2.setCity("Kelowna");
				owner2.setTelephone("+1 (123) 123 1234");
				
				Pet fionasCat = new Pet();
				fionasCat.setName("Zeus");
				fionasCat.setOwner(owner2);
				fionasCat.setBirthDate(LocalDate.now());
				fionasCat.setPetType(saveCaPetType);
				owner2.getPets().add(fionasCat);
				
				ownerService.save(owner2);
				
				System.out.println("Loaded Owner...");
				
		//		Vets Data loader
				Vet vet1 = new Vet();
				vet1.setFirstName("Sam");
				vet1.setLastName("Axe");
				vet1.getSpecialities().add(savedRadiology);
				
				vetService.save(vet1);
				
				Vet vet2 = new Vet();
				vet2.setFirstName("Jessie");
				vet2.setLastName("Porter");
				vet2.getSpecialities().add(savedSurgery);
				
				vetService.save(vet2);
				
				System.out.println("Loaded Vets...");
	}
	
}
