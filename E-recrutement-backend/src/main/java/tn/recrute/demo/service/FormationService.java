package tn.recrute.demo.service;

import java.util.List;
import java.util.Optional;

import tn.recrute.demo.model.Formation;


public interface FormationService {

    public Formation addFormation(Formation formation);
	
	public Formation updateFormation(Formation formation);
	
	public List<Formation> getAll();
	
	public void deleteFormation(Long id);
	
	public Formation findById(Long id);
	
	public Optional<Formation> findByTitle(String title);
}
