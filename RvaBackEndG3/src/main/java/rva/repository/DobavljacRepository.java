package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Dobavljac;

public interface DobavljacRepository extends JpaRepository<Dobavljac,Long>{

	List<Dobavljac> findByAdresaContainingIgnoreCase(String adresa);
	List<Dobavljac> findByNazivContainingIgnoreCase(String naziv);
}
