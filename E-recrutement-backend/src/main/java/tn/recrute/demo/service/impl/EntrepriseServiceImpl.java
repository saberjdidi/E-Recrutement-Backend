package tn.recrute.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.recrute.demo.model.Entreprise;
import tn.recrute.demo.repository.EntrepriseRepository;
import tn.recrute.demo.service.EntrepriseService;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Override
	public long addEntreprise(Entreprise entreprise) {
		
		System.out.println("save company");
		Entreprise company = new Entreprise();
		company.setName(entreprise.getName());
		company.setDescription(entreprise.getDescription());
		company.setAddress(entreprise.getAddress());
		company.setFileName(entreprise.getFileName());
		company.setType(entreprise.getType());
		company.setPicByte(entreprise.getPicByte());
		
		return this.entrepriseRepository.save(company).getId();
	}

	@Override
	public Entreprise updateEntreprise(Entreprise entreprise) {
		
		return this.entrepriseRepository.save(entreprise);
	}

	@Override
	public List<Entreprise> getAll() {
		
		return this.entrepriseRepository.findAll();
	}

	@Override
	public void deleteEntreprise(Long id) {
		
		this.entrepriseRepository.deleteById(id);
	}

	@Override
	public Entreprise findById(Long id) {
		
		return this.entrepriseRepository.findById(id).get();
	}


}
