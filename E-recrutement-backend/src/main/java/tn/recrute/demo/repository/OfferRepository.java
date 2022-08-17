package tn.recrute.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.recrute.demo.model.Entreprise;
import tn.recrute.demo.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
	
	Set<Offer> findByEntreprise(Entreprise entreprise);
}
