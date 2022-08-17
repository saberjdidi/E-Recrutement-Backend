package tn.recrute.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import tn.recrute.demo.model.Entreprise;
import tn.recrute.demo.model.Offer;
import tn.recrute.demo.service.EntrepriseService;
import tn.recrute.demo.service.OfferService;

@RestController
@RequestMapping("/offer")
@CrossOrigin("*")
public class OfferController {

	@Autowired
	private OfferService offerService;
	
	@Autowired
	private EntrepriseService entrepriseService;
	
	@PostMapping(path="/save")
	public ResponseEntity<?> saveEntity(@RequestBody Offer offer) {
		
		Offer entity = this.offerService.addOffer(offer);
		return ResponseEntity.ok(entity);
	}
	
	@PutMapping(path="/update")
	public ResponseEntity<?> updateEntity(@RequestBody Offer offer) {
		
		Offer entity = this.offerService.updateOffer(offer);
		return ResponseEntity.ok(entity);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		
		return ResponseEntity.ok(this.offerService.getOffers());
	}
	
	//get all offers with Company
	@GetMapping("/company/{CompanyId}")
	public ResponseEntity<?> getOffersOfCompany(@PathVariable("CompanyId") Long id) {
		//first method
		Entreprise entreprise = this.entrepriseService.findById(id);
		Set<Offer> offers = entreprise.getOffers();
		List<Offer> list = new ArrayList<>(offers);
		//return ResponseEntity.ok(list);
		
		//second method
		Entreprise entreprise2 = new Entreprise();
		entreprise2.setId(id);
		Set<Offer> offersCompany = this.offerService.getOffersOfCompany(entreprise2);
		
		return ResponseEntity.ok(offersCompany);
	}
	
	@GetMapping("/{offerId}")
	public Offer getOfferById(@PathVariable("offerId") Long id) {
		
		return this.offerService.getById(id);
	}
	
	@DeleteMapping("/{offerId}")
	public void deleteCategory(@PathVariable("offerId") Long id) {
		
		this.offerService.deleteOffer(id);
	}
}
