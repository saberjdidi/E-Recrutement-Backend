package tn.recrute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.recrute.demo.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
