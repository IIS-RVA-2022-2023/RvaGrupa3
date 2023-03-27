package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Dobavljac;
import rva.model.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long> {

	List<Porudzbina> findByIznosGreaterThanOrderById(double iznos);
	List<Porudzbina> findByDobavljac(Dobavljac dobavljac);
}
