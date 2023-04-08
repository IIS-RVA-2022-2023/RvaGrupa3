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

import rva.model.StavkaPorudzbine;
import rva.service.PorudzbinaService;
import rva.service.StavkaPorudzbineService;

@RestController
public class StavkaPorudzbineController {

	@Autowired
	private StavkaPorudzbineService service;

	@Autowired
	private PorudzbinaService porudzbinaService;

	@GetMapping("/stavkaPorudzbine")
	public List<StavkaPorudzbine> getAllStavkaPorudzbine() {
		return service.getAll();
	}

	@GetMapping("/stavkaPorudzbine/{id}")
	public ResponseEntity<?> findStavkaPorudzbineById(@PathVariable long id) {
		if (service.findById(id).isPresent()) {
			StavkaPorudzbine stavkaPorudzbine = service.findById(id).get();
			return ResponseEntity.ok(stavkaPorudzbine);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID:" + id + " has not been found");
		}
	}

	@GetMapping("/stavkaPorudzbine/cena/{cena}")
	public ResponseEntity<?> findStavkaPorudzbineByCenaLessThan(@PathVariable double cena) {
		if (!service.findByCenaLessThanOrderById(cena).isEmpty()) {
			return ResponseEntity.ok(service.findByCenaLessThanOrderById(cena));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resources with cena less than " + cena + " have not been found");
		}
	}

	@GetMapping("/stavkaPorudzbine/porudzbina/{id}")
	public ResponseEntity<?> getStavkePorudzbineByPorudzbina(@PathVariable long id) {
		if (!service.findStavkaPorudzbineByPorudzbina(porudzbinaService.findById(id).get()).get().isEmpty()) {
			return ResponseEntity.ok(service.findStavkaPorudzbineByPorudzbina(porudzbinaService.findById(id).get()).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resources with requested value of foreign key porudzbina:" + id + "have not been found");
		}
	}

	@PostMapping("stavkaPorudzbine")
	public ResponseEntity<StavkaPorudzbine> createStavkaPorudzbine(@RequestBody StavkaPorudzbine stavkaPorudzbine) {
		StavkaPorudzbine savedStavkaPorudzbine;

		if (!service.existsById(stavkaPorudzbine.getId())) {
			savedStavkaPorudzbine = service.addStavkaPorudzbine(stavkaPorudzbine);
		} else {
			List<StavkaPorudzbine> lista = service.getAll();
			long najvecaVrednost = 1;
			for (int i = 0; i < lista.size(); i++) {
				if (najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}

				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}

			}
			stavkaPorudzbine.setId(najvecaVrednost);
			savedStavkaPorudzbine = service.addStavkaPorudzbine(stavkaPorudzbine);
		}

		URI uri = URI.create("/stavkaPorudzbine/" + savedStavkaPorudzbine.getId());
		return ResponseEntity.created(uri).body(savedStavkaPorudzbine);
	}
	
	@PutMapping("stavkaPorudzbine/{id}")
	public ResponseEntity<?> updateStavkaPorudzbine(@RequestBody StavkaPorudzbine stavkaPorudzbine, @PathVariable long id){
		if(service.existsById(id)) {
			stavkaPorudzbine.setId(id);
			return ResponseEntity.ok(service.addStavkaPorudzbine(stavkaPorudzbine));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: " + id +" has not been found");
		}
	}
	
	@DeleteMapping("stavkaPorudzbine/{id}")
	public ResponseEntity<?> deleteStavkaPorudzbine(@PathVariable long id){
		if(service.existsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with ID:" + id + " has been deleted");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID:"+ id + " has not been found");
		}
	}

	
}
