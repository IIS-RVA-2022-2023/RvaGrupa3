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

import rva.model.Porudzbina;
import rva.service.DobavljacService;
import rva.service.PorudzbinaService;

@RestController
@CrossOrigin
public class PorudzbinaController {

	@Autowired
	private PorudzbinaService service;

	@Autowired
	private DobavljacService dobavljacService;

	@GetMapping("/porudzbina")
	public List<Porudzbina> getAllPorudzbina() {
		return service.getAll();
	}

	@GetMapping("/porudzbina/{id}")
	public ResponseEntity<?> findPorudzbinaById(@PathVariable long id) {
		if (service.findById(id).isPresent()) {
			Porudzbina porudzbina = service.findById(id).get();
			return ResponseEntity.ok(porudzbina);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID:" + id + " has not been found");
		}
	}

	@GetMapping("/porudzbina/iznos/{iznos}")
	public ResponseEntity<?> findPorudzbinaByIznosGreaterThan(@PathVariable double iznos) {
		if (!service.findByIznosGreaterThan(iznos).get().isEmpty()) {
			return ResponseEntity.ok(service.findByIznosGreaterThan(iznos).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resources with iznos greater than " + iznos + " have not been found");
		}
	}

	@GetMapping("/porudzbina/dobavljac/{id}")
	public ResponseEntity<?> getPorudzbineByDobavljac(@PathVariable long id) {
		if (!service.findPorudzbinaByDobavljac(dobavljacService.findById(id).get()).get().isEmpty()) {
			return ResponseEntity.ok(service.findPorudzbinaByDobavljac(dobavljacService.findById(id).get()).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resources with requested value of foreign key dobavljac:" + id + "have not been found");
		}
	}

	@PostMapping("porudzbina")
	public ResponseEntity<Porudzbina> createPorudzbina(@RequestBody Porudzbina porudzbina) {
		Porudzbina savedPorudzbina;

		if (!service.existsById(porudzbina.getId())) {
			savedPorudzbina = service.addPorudzbina(porudzbina);
		} else {
			List<Porudzbina> lista = service.getAll();
			long najvecaVrednost = 1;
			for (int i = 0; i < lista.size(); i++) {
				if (najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}

				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}

			}
			porudzbina.setId(najvecaVrednost);
			savedPorudzbina = service.addPorudzbina(porudzbina);
		}

		URI uri = URI.create("/porudzbina/" + savedPorudzbina.getId());
		return ResponseEntity.created(uri).body(savedPorudzbina);
	}
	
	@PutMapping("porudzbina/{id}")
	public ResponseEntity<?> updatePorudzbina(@RequestBody Porudzbina porudzbina, @PathVariable long id){
		if(service.existsById(id)) {
			porudzbina.setId(id);
			return ResponseEntity.ok(service.addPorudzbina(porudzbina));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: " + id +" has not been found");
		}
	}
	
	@DeleteMapping("porudzbina/{id}")
	public ResponseEntity<?> deletePorudzbina(@PathVariable long id){
		if(service.existsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with ID:" + id + " has been deleted");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID:"+ id + " has not been found");
		}
	}

}
