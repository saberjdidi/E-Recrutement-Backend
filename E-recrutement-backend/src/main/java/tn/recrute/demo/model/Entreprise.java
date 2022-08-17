package tn.recrute.demo.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="entreprise")
public class Entreprise {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "id")
	private Long id;
	private String name;
	private String description;
	private String address;
	private String fileName;
	private String type;
	
	 //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
	//@Column(name = "picByte", length = 1000)
	private byte[] picByte;
	
	@OneToMany(mappedBy="entreprise", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Formation> formations = new LinkedHashSet<>();
	
	@OneToMany(mappedBy="entreprise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Offer> offers = new HashSet<>();

	public Entreprise() {
	}

	public Entreprise(String name, String description, String address, String fileName, String type, byte[] picByte) {
		
		this.name = name;
		this.description = description;
		this.address = address;
		this.fileName = fileName;
		this.type = type;
		this.picByte = picByte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public Set<Formation> getFormations() {
		return formations;
	}

	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	
	
}
