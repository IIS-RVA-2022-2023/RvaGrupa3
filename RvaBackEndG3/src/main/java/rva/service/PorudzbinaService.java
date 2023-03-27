package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Dobavljac;
import rva.model.Porudzbina;
import rva.repository.PorudzbinaRepository;

@Service
public class PorudzbinaService {

	@Autowired
	private PorudzbinaRepository repo;
	
	public List<Porudzbina> getAll(){
		return repo.findAll();
	}
	
	public Optional<Porudzbina> findById(long id){
		return repo.findById(id);
	}
	
	public Optional<List<Porudzbina>> findByIznosGreaterThan(double iznos){
		Optional<List<Porudzbina>> lista = Optional.of(repo.findByIznosGreaterThanOrderById(iznos));
		return lista;
	}
	
	public Optional<List<Porudzbina>> findPorudzbinaByDobavljac(Dobavljac dobavljac){
		Optional<List<Porudzbina>> lista = Optional.of(repo.findByDobavljac(dobavljac));
		return lista;
	}
	
	public Porudzbina addPorudzbina(Porudzbina porudzbina) {
		return repo.save(porudzbina);
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
