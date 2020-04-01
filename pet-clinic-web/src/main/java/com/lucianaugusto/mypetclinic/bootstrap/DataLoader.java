package com.lucianaugusto.mypetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lucianaugusto.mypetclinic.model.Owner;
import com.lucianaugusto.mypetclinic.model.Vet;
import com.lucianaugusto.mypetclinic.services.OwnerService;
import com.lucianaugusto.mypetclinic.services.VetService;
import com.lucianaugusto.mypetclinic.services.map.OwnerServiceMap;
import com.lucianaugusto.mypetclinic.services.map.VetServiceMap;

@Component // By annotating this as a component, this becomes a spring bean and gets registered into the spring context
public class DataLoader implements CommandLineRunner {
	
	private final OwnerService ownerService;
	private final VetService vetService;

	public DataLoader() {
		ownerService = new OwnerServiceMap();
		vetService = new VetServiceMap();
	}

	@Override
	public void run(String... args) throws Exception {
		
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");
		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owner...");
		
		Vet vet1 = new Vet();
		vet1.setId(1L);
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setId(2L);
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets...");
		
	}
	
}
