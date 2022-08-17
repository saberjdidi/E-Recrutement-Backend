package tn.recrute.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.recrute.demo.model.Contact;
import tn.recrute.demo.service.ContactService;
import tn.recrute.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository ContactRepository;

	@Override
	public Contact addContact(Contact contact) {
		
		return this.ContactRepository.save(contact);
	}

	@Override
	public Contact updateContact(Contact contact) {
		
		return this.ContactRepository.save(contact);
	}

	@Override
	public List<Contact> getContacts() {
		
		return this.ContactRepository.findAll();
	}

	@Override
	public void deleteContact(Long id) {
		
		this.ContactRepository.deleteById(id);
	}

	@Override
	public Contact getContactById(Long id) {
		
		return this.ContactRepository.findById(id).get();
	}

}
