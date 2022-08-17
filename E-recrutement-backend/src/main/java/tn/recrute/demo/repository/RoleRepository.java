package tn.recrute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.recrute.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
