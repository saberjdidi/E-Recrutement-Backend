package tn.recrute.demo.service;

import java.util.List;
import java.util.Set;

import tn.recrute.demo.model.Candidat;
import tn.recrute.demo.model.Offer;

public interface CandidatService {

    public Candidat addCandidat(Candidat candidat);
	
	public List<Candidat> getCandidats();
	
	public void deleteCandidat(Long id);
	
	public Candidat findById(Long id);
	
	public Set<Candidat> getCandidatsOfOffer(Offer offer);
}
