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
public class Dobavljac implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "DOBAVLJAC_ID_GENERATOR", sequenceName = "DOBAVLJAC_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOBAVLJAC_ID_GENERATOR")
	private long id;
	private String naziv;
	private String adresa;
	private String kontakt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "dobavljac", cascade = CascadeType.REMOVE)
	private List<Porudzbina> porudzbine;

	public Dobavljac() {
		
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public List<Porudzbina> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(List<Porudzbina> porudzbine) {
		this.porudzbine = porudzbine;
	}

}
