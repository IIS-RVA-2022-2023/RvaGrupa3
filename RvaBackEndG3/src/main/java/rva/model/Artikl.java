package rva.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Artikl implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "ARTIKL_ID_GENERATOR", sequenceName = "ARTIKL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTIKL_ID_GENERATOR")
	private long id;
	
	private String naziv;
	private String proizvodjac;
	
	@JsonIgnore
	@OneToMany(mappedBy = "artikl", cascade = CascadeType.REMOVE)
	private List<StavkaPorudzbine> stavke;
	
	public Artikl() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getProizvodjac() {
		return proizvodjac;
	}
	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public List<StavkaPorudzbine> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaPorudzbine> stavke) {
		this.stavke = stavke;
	}
	
	
	
}
