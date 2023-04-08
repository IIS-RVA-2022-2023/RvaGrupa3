package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Porudzbina;
import rva.model.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Long> {

	List<StavkaPorudzbine> findByPorudzbina(Porudzbina porudzbina);
    List<StavkaPorudzbine> findByCenaLessThanOrderById(double cena);
}
