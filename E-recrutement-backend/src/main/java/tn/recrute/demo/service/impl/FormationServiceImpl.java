package tn.recrute.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.recrute.demo.model.Formation;
import tn.recrute.demo.repository.FormationRepository;
import tn.recrute.demo.service.FormationService;

@Service
public class FormationServiceImpl implements FormationService {

	@Autowired
	private FormationRepository formationRepository;
	
	@Override
	public Formation addFormation(Formation formation) {
		
		System.out.println("save training");
		/*Formation training = new Formation();
		training.setTitle(formation.getTitle());
		training.setDescription(formation.getDescription());
		training.setAddress(formation.getAddress());
		training.setDate(formation.getDate());
		training.setDuration(formation.getDuration());
		training.setPhone(formation.getPhone());
		training.setFileName(formation.getFileName()); 
		training.setEntreprise(formation.getEntreprise()); */
		
		return this.formationRepository.save(formation);
	}

	@Override
	public List<Formation> getAll() {
		System.out.println("get all data");
		return this.formationRepository.findAll();
	}

	@Override
	public void deleteFormation(Long id) {
		
		this.formationRepository.deleteById(id);
	}

	@Override
	public Formation findById(Long id) {
		
		return this.formationRepository.findById(id).get();
	}

	@Override
	public Optional<Formation> findByTitle(String title) {
		
		return this.formationRepository.findByTitle(title);
	}

	@Override
	public Formation updateFormation(Formation formation) {
		
		return this.formationRepository.save(formation);
	}

}
