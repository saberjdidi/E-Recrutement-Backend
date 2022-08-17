package tn.recrute.demo.service;

import java.util.List;
import java.util.Optional;

import tn.recrute.demo.model.Entreprise;

public interface EntrepriseService {

    public long addEntreprise(Entreprise entreprise);
	
	public Entreprise updateEntreprise(Entreprise entreprise);
	
	public List<Entreprise> getAll();
	
	public void deleteEntreprise(Long id);
	
	public Entreprise findById(Long id);
}
