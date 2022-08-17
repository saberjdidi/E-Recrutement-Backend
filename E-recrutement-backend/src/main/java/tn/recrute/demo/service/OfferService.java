package tn.recrute.demo.service;

import java.util.List;
import java.util.Set;

import tn.recrute.demo.model.Entreprise;
import tn.recrute.demo.model.Offer;

public interface OfferService {

	public Offer addOffer(Offer offer);
	
	public Offer updateOffer(Offer offer);
	
	public List<Offer> getOffers();
	
	public void deleteOffer(Long id);
	
	public Offer getById(Long id);
	
	public Set<Offer> getOffersOfCompany(Entreprise entreprise);
}
