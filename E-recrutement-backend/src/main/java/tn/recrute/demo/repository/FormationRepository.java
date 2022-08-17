package tn.recrute.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.recrute.demo.model.Formation;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {

	Optional<Formation> findByTitle(String title);
}
