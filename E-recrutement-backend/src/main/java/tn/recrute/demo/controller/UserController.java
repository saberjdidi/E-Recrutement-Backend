package tn.recrute.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.recrute.demo.helper.UserFoundException;
import tn.recrute.demo.model.Formation;
import tn.recrute.demo.model.Response;
import tn.recrute.demo.model.Role;
import tn.recrute.demo.model.User;
import tn.recrute.demo.model.UserRole;
import tn.recrute.demo.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder pCryptPasswordEncoder;
	
	@Autowired  ServletContext context;
	
	//createUser
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		user.setProfile("default.png");
		
		user.setPassword(this.pCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> roles = new HashSet<>();
		
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("USER");
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		roles.add(userRole);
		
		return this.userService.CreateUser(user, roles);
	}
	
	   //createUser with image
	    @PostMapping("/save")
		public ResponseEntity<Response> saveUser (@RequestParam("file") MultipartFile file,
				 @RequestParam("user") String user) throws JsonParseException, JsonMappingException, Exception
		 {
			 System.out.println("Ok .............");
	      User entity = new ObjectMapper().readValue(user, User.class);
	      boolean isExit = new File(context.getRealPath("/Profiles/")).exists();
	      if (!isExit)
	      {
	      	new File (context.getRealPath("/Profiles/")).mkdir();
	      	System.out.println("mk dir.............");
	      }
	      String filename = file.getOriginalFilename();
	      String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
	      File serverFile = new File (context.getRealPath("/Profiles/"+File.separator+newFileName));
	      try
	      {
	      	System.out.println("Image");
	      	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
	      	 
	      }catch(Exception e) {
	      	e.printStackTrace();
	      }

	     
	      entity.setProfile(newFileName);
			
	      entity.setPassword(this.pCryptPasswordEncoder.encode(entity.getPassword()));
			
			Set<UserRole> roles = new HashSet<>();
			
			Role role = new Role();
			role.setRoleId(45L);
			role.setRoleName("USER");
			
			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUser(entity);
			
			roles.add(userRole);
			
	      User utilisateur = this.userService.CreateUser(entity, roles);
	      if (utilisateur != null)
	      {
	      	return new ResponseEntity<Response>(new Response ("user save successfully"),HttpStatus.OK);
	      }
	      else
	      {
	      	return new ResponseEntity<Response>(new Response ("user not saved"),HttpStatus.BAD_REQUEST);	
	      }
		 }
	    
	    @PutMapping("/update")
		public User updateUser(@RequestBody User user) {
			
			return this.userService.updateUser(user);
		}
	
	@GetMapping("/all")
	public ResponseEntity<?> getUsers(){
		return ResponseEntity.ok(this.userService.getUsers());
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		
		return this.userService.getUser(username);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long id) {
		this.userService.deleteUser(id);
	}
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException ex){
		return (ResponseEntity<?>) ResponseEntity.ok();
	}
	
	
	@GetMapping("/image/{id}")
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
		
		User user = this.userService.findById(id);
		
		return Files.readAllBytes(Paths.get(context.getRealPath("/Profiles/")+user.getProfile()));
	}
}
