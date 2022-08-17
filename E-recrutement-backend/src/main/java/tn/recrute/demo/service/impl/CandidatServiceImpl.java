package tn.recrute.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.recrute.demo.model.Candidat;
import tn.recrute.demo.model.Offer;
import tn.recrute.demo.repository.CandidatRepository;
import tn.recrute.demo.service.CandidatService;

@Service
public class CandidatServiceImpl implements CandidatService {

	@Autowired
	private CandidatRepository candidatRepository;
	
	@Override
	public Candidat addCandidat(Candidat candidat) {
		
		return this.candidatRepository.save(candidat);
	}

	@Override
	public List<Candidat> getCandidats() {
		
		return this.candidatRepository.findAll();
	}

	@Override
	public void deleteCandidat(Long id) {
		
		this.candidatRepository.deleteById(id);
	}

	@Override
	public Candidat findById(Long id) {
		
		return this.candidatRepository.findById(id).get();
	}

	@Override
	public Set<Candidat> getCandidatsOfOffer(Offer offer) {
		
		return new HashSet<>(this.candidatRepository.findByOffer(offer));
	}

}
