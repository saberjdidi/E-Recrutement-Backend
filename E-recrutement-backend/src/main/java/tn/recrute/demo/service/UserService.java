package tn.recrute.demo.service;

import java.util.List;
import java.util.Set;

import tn.recrute.demo.model.User;
import tn.recrute.demo.model.UserRole;

public interface UserService {

	public User CreateUser(User user, Set<UserRole> userRoles) throws Exception;
	
	public User updateUser(User user);
	
	public User getUser(String username);
	
	public User findById(Long id);
	
	public void deleteUser(Long userId);
	
	public List<User> getUsers();
}
