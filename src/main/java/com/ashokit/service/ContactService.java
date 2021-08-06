package com.ashokit.service;

import java.util.List;

import com.ashokit.entity.Contact;

public interface ContactService {

	public boolean saveContact(Contact contact);
	
	public List<Contact> viewAllContacts();
	
	public Contact getContactById(Integer contactId);
	
	public boolean deleteContactById(Integer contactId);
	
}
