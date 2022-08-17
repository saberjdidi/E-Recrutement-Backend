package tn.recrute.demo.service;

import java.util.List;

import tn.recrute.demo.model.Contact;


public interface ContactService {

    public Contact addContact(Contact contact);
	
	public Contact updateContact(Contact contact);
	
	public List<Contact> getContacts();
	
	public void deleteContact(Long id);
	
	public Contact getContactById(Long id);
}
