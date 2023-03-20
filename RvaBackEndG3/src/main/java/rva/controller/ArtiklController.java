package rva.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Artikl;
import rva.service.ArtiklService;

@RestController
public class ArtiklController {

	
	@Autowired
	private ArtiklService service;
	
	@GetMapping("/artikl")
	public List<Artikl> getAllArtikls(){
		return service.getAll();
	}
	
	@GetMapping("/artikl/{id}")
	public ResponseEntity<?> getArtiklById(@PathVariable long id){
		if(service.existsById(id)) {
			return ResponseEntity.ok(service.getById(id).get());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).
					body("Requested resource does not exist");
		}
	}
	
	@GetMapping("/artikl/naziv/{naziv}")
	public ResponseEntity<?> getArtiklsByNaziv(@PathVariable String naziv){
		List<Artikl> artikli = service.getByNaziv(naziv).get();
		if(!artikli.isEmpty()) {
			return ResponseEntity.ok(artikli);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resources with requested naziv: " + naziv + " are not found");
		}
	}
	
	@PostMapping("/artikl")
	public ResponseEntity<?> createArtikl(@RequestBody Artikl artikl){
		Artikl savedArtikl;
		
		if(!service.existsById(artikl.getId())) {
			savedArtikl = service.addArtikl(artikl);
		}else {
			List<Artikl> lista = service.getAll();
			long najvecaVrednost = 1;
			for(int i = 0; i < lista.size(); i++) {
				if(najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}
				
				if(i == lista.size()-1) {
					najvecaVrednost++;
				}
				
			}
			artikl.setId(najvecaVrednost);
			savedArtikl = service.addArtikl(artikl);
		}
		
		
		URI uri = URI.create("/artikl/" + savedArtikl.getId());
		return ResponseEntity.created(uri).body(savedArtikl);
	}
	
	@PutMapping("/artikl/{id}")
	public ResponseEntity<?> updateArtikl(@RequestBody Artikl artikl, @PathVariable long id){
		if(service.existsById(id)) {
			Artikl savedArtikl = service.addArtikl(artikl);
			return ResponseEntity.ok(savedArtikl);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).
					body("Resource with requested ID: " + id + " has not been found");
		}
	}
	
	@DeleteMapping("/artikl/{id}")
	public ResponseEntity<String> deleteArtikl(@PathVariable long id){
		if(service.existsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with requested ID: " + id + " has been deleted");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: " + id + " has not been found");
		}
	}
	
	
	
}
