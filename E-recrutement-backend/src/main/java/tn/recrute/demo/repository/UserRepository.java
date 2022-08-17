package tn.recrute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.recrute.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
	public User findByEmail(String email);
}
