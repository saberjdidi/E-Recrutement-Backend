package tn.recrute.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.recrute.demo.model.Entreprise;
import tn.recrute.demo.model.Offer;
import tn.recrute.demo.repository.OfferRepository;
import tn.recrute.demo.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository offerRepository;
	
	@Override
	public Offer addOffer(Offer offer) {
		
		return this.offerRepository.save(offer);
	}

	@Override
	public Offer updateOffer(Offer offer) {
		
		return this.offerRepository.save(offer);
	}

	@Override
	public List<Offer> getOffers() {
		
		return this.offerRepository.findAll();
	}

	@Override
	public void deleteOffer(Long id) {
		
		this.offerRepository.deleteById(id);
	}

	@Override
	public Offer getById(Long id) {
		
		return this.offerRepository.findById(id).get();
	}

	@Override
	public Set<Offer> getOffersOfCompany(Entreprise entreprise) {
		
		return new HashSet<>(this.offerRepository.findByEntreprise(entreprise));
	}

}
