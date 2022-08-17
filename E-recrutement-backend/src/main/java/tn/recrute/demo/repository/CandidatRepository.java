package tn.recrute.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.recrute.demo.model.Candidat;
import tn.recrute.demo.model.Offer;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {

	Set<Candidat> findByOffer(Offer offer);
}
