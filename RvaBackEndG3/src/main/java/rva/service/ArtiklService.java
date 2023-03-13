package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Artikl;
import rva.repository.ArtiklRepository;

@Service
public class ArtiklService {

	@Autowired
	private ArtiklRepository repo;
	
	
	public List<Artikl> getAll(){
		return repo.findAll();
	}
	
	public Optional<Artikl> getById(long id){
		return repo.findById(id);
	}
	
	public Optional<List<Artikl>> getByNaziv(String naziv){
		Optional<List<Artikl>> artikli = Optional.of(repo.findByNazivContainingIgnoreCase(naziv));
		return artikli;
	}
	
	public Artikl addArtikl(Artikl artikl) {
		return repo.save(artikl);
	}
	
	public boolean existsById(long id) {
		return getById(id).isPresent();
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
	
}
