package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Porudzbina;
import rva.model.StavkaPorudzbine;
import rva.repository.StavkaPorudzbineRepository;

@Service
public class StavkaPorudzbineService {

	@Autowired
	private StavkaPorudzbineRepository repo;
	
	public List<StavkaPorudzbine> getAll(){
		return repo.findAll();
	}
	
	public Optional<StavkaPorudzbine> findById(long id){
		return repo.findById(id);
	}
	
	public List<StavkaPorudzbine> findByCenaLessThanOrderById(double cena) {
        return repo.findByCenaLessThanOrderById(cena);
    }
	
	public Optional<List<StavkaPorudzbine>> findStavkaPorudzbineByPorudzbina(Porudzbina porudzbina){
		Optional<List<StavkaPorudzbine>> lista = Optional.of(repo.findByPorudzbina(porudzbina));
		return lista;
	}
	
	public StavkaPorudzbine addStavkaPorudzbine(StavkaPorudzbine stavkaPorudzbine) {
		return repo.save(stavkaPorudzbine);
	}
	
	public boolean existsById(long id) {
		if(findById(id).isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
    
    
}
