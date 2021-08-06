package com.ashokit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.ashokit.entity.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer>{

	public List<Contact> findByActiveSwitch(String activeSwitch);
	
}
