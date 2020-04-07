package com.lucianaugusto.mypetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.model.PetType;
import com.lucianaugusto.mypetclinic.model.Vet;
import com.lucianaugusto.mypetclinic.services.OwnerService;
import com.lucianaugusto.mypetclinic.services.PetTypeService;
import com.lucianaugusto.mypetclinic.services.VetService;
import com.lucianaugusto.mypetclinic.services.map.OwnerServiceMap;
import com.lucianaugusto.mypetclinic.services.map.VetServiceMap;

@Component // By annotating this as a component, this becomes a spring bean and gets registered into the spring context
public class DataLoader implements CommandLineRunner {
	
	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) { // Takes any implementations of both interfaces and
		// bring them into the context by using Spring to inject the dependencies. Constructor-based Dependency Injection,
		// doesn't require the @Autowired annotation.
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Owner Data Loader
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");
		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owner...");
		
//		Vets Data loader
		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets...");
		
//		PetType data loader
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType saveDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType saveCaPetType = petTypeService.save(cat);
		
		System.out.println("Loaded PetTypes...");
		
	}
	
}
