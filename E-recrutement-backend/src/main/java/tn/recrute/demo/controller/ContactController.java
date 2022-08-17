package tn.recrute.demo.controller;

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

import tn.recrute.demo.model.Contact;
import tn.recrute.demo.service.ContactService;

@RestController
@CrossOrigin("*")
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@PostMapping(path="/save")
	public ResponseEntity<?> saveContact(@RequestBody Contact contact) {
		
		Contact entity = this.contactService.addContact(contact);
		
		return ResponseEntity.ok(entity);
	}
	
	@PutMapping(path="/update")
	public ResponseEntity<?> updateContact(@RequestBody Contact contact) {
		
		Contact entity = this.contactService.addContact(contact);
		
		return ResponseEntity.ok(entity);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getContacts(){
		
		return ResponseEntity.ok(this.contactService.getContacts());
	}
	
	@GetMapping("/{contactId}")
	public Contact getContactById(@PathVariable("contactId") Long id) {
		
		return this.contactService.getContactById(id);
	}
	
	@DeleteMapping("/{contactId}")
	public void deleteCategory(@PathVariable("contactId") Long id) {
		
		this.contactService.deleteContact(id);
	}
}
