package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Dobavljac;
import rva.repository.DobavljacRepository;

@Service
/*
 * Anotacija koja Spring kontejneru daje do znanja da treba da napravi bean
 * (objekat sa svim zavisnostima) klase DobavljacService 
 */
public class DobavljacService {

	@Autowired
	/*
	 * Anotacija koja predstavlja Dependency Injection. 
	 * Moze biti navedena iznad reference, set metode ili konstruktora
	 * U ovom slucaju, kada je navedena iznad reference tipa interfejsa
	 * Spring kontejner kreira anonimnu klasu koja implementira interfejs i njegove metode, 
	 * instancira objekat anonimne klase i referencu na taj objekat injektuje u referencu nad
	 * kojom se ova anotacija nalazi. U slucaju obicne klase se samo vrsi instanciranje objekta
	 * i injektovanje reference na taj objekat
	 * */
	private DobavljacRepository repo;
	
	public List<Dobavljac> getAll(){
		return repo.findAll();
	}
	
	/*
	 * Optional je poseban wrapper tip podatka kreiran u Java verziji 8
	 */
	public Optional<Dobavljac> findById(long id){
		return repo.findById(id);
	}
	
	public Optional<List<Dobavljac>> getByNaziv(String naziv){
		Optional<List<Dobavljac>> dobavljaci = Optional.of(repo.findByNazivContainingIgnoreCase(naziv));
		return dobavljaci;
	}
	
	public Optional<List<Dobavljac>> getByAdresa(String adresa){
		Optional<List<Dobavljac>> dobavljaci = Optional.of(repo.findByAdresaContainingIgnoreCase(adresa));
		return dobavljaci;
	}
	
	public Dobavljac addDobavljac(Dobavljac dobavljac) {
		return repo.save(dobavljac);
	}
	
	public boolean existsById(long id) {
		return findById(id).isPresent();
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
}
