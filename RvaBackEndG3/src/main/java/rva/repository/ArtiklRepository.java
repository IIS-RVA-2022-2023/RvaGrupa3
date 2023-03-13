package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Artikl;

public interface ArtiklRepository extends JpaRepository<Artikl,Long> {

	List<Artikl> findByNazivContainingIgnoreCase(String naziv);
}
