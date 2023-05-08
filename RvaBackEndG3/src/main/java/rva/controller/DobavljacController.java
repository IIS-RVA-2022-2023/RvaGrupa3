package rva.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Dobavljac;
import rva.service.DobavljacService;

@RestController
@CrossOrigin
public class DobavljacController {

	@Autowired
	private DobavljacService service;
	
	@GetMapping("/dobavljac")
	public List<Dobavljac> getAllDobavljacs(){
		return service.getAll();
	}
	
	@GetMapping("/dobavljac/{id}")
	public ResponseEntity<?> getDobavljacById(@PathVariable long id){
		if(service.existsById(id)) {
			return ResponseEntity.ok(service.findById(id).get());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).
					body("Requested resource does not exist");
		}
	}
	
	@GetMapping("/dobavljac/naziv/{naziv}")
	public ResponseEntity<?> getDobavljacsByNaziv(@PathVariable String naziv){
		List<Dobavljac> dobavljaci = service.getByNaziv(naziv).get();
		if(!dobavljaci.isEmpty()) {
			return ResponseEntity.ok(dobavljaci);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resources with requested naziv: " + naziv + " are not found");
		}
	}
	
	@PostMapping("/dobavljac")
	public ResponseEntity<?> createDobavljac(@RequestBody Dobavljac dobavljac){
		Dobavljac savedDobavljac;
		
		if(!service.existsById(dobavljac.getId())) {
			savedDobavljac = service.addDobavljac(dobavljac);
		}else {
			List<Dobavljac> lista = service.getAll();
			long najvecaVrednost = 1;
			for(int i = 0; i < lista.size(); i++) {
				if(najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}
				
				if(i == lista.size()-1) {
					najvecaVrednost++;
				}
				
			}
			dobavljac.setId(najvecaVrednost);
			savedDobavljac = service.addDobavljac(dobavljac);
		}
		
		
		URI uri = URI.create("/dobavljac/" + savedDobavljac.getId());
		return ResponseEntity.created(uri).body(savedDobavljac);
	}
	
	@PutMapping("/dobavljac/{id}")
	public ResponseEntity<?> updateDobavljac(@RequestBody Dobavljac dobavljac, @PathVariable long id){
		if(service.existsById(id)) {
			dobavljac.setId(id);
			Dobavljac savedDobavljac = service.addDobavljac(dobavljac);
			return ResponseEntity.ok(savedDobavljac);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).
					body("Resource with requested ID: " + id + " has not been found");
		}
	}
	
	@DeleteMapping("/dobavljac/{id}")
	public ResponseEntity<String> deleteDobavljac(@PathVariable long id){
		if(service.existsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with requested ID: " + id + " has been deleted");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: " + id + " has not been found");
		}
	}
}
