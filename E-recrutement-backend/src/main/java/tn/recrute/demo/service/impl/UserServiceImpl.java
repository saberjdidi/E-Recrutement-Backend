package tn.recrute.demo.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.recrute.demo.helper.UserFoundException;
import tn.recrute.demo.model.User;
import tn.recrute.demo.model.UserRole;
import tn.recrute.demo.repository.RoleRepository;
import tn.recrute.demo.repository.UserRepository;
import tn.recrute.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User CreateUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = userRepository.findByUsername(user.getUsername());
		if(local != null) {
			System.out.println("User already exist !");
			throw new UserFoundException();
		} else {
			//create user
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
		   local = this.userRepository.save(user);
		}
		
		return local;
	}

	@Override
	public User getUser(String username) {
		
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		
		this.userRepository.deleteById(userId);
	}

	@Override
	public List<User> getUsers() {
		
		return this.userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		
		return this.userRepository.save(user);
	}

	@Override
	public User findById(Long id) {
		
		return this.userRepository.findById(id).get();
	}

}
