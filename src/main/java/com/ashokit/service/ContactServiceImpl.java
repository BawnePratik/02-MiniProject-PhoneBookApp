package com.ashokit.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashokit.entity.Contact;
import com.ashokit.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactRepository repo;
	
	@Override
	public boolean saveContact(Contact contact) {
		contact.setActiveSwitch("Y");
		Contact savedObject = repo.save(contact);	
		return savedObject.getContactId() != null;
	}

	@Override
	public List<Contact> viewAllContacts() {
		return repo.findByActiveSwitch("Y");
	}

	@Override
	public Contact getContactById(Integer contactId) {
		Optional<Contact> findById = repo.findById(contactId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean deleteContactById(Integer contactId) {
		try {
			Contact contact = repo.findById(contactId).get();
			contact.setActiveSwitch("N");
			repo.save(contact);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
