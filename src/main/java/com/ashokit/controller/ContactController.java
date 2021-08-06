package com.ashokit.controller;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ashokit.constants.AppConstants;
import com.ashokit.entity.Contact;
import com.ashokit.props.AppProperties;
import com.ashokit.service.ContactService;

@Controller
public class ContactController {
	
	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	private Integer contactId;
	private String cName= "";
	private String contactMsg = "";
	
	@Autowired(required = true)
	private ContactService service;
	
	@Autowired
	private AppProperties appProps;
	
	@GetMapping("/loadForm")
	public String loadForm(Model model) {
		logger.debug("****loadForm() method execution started****");
		Contact contact = new Contact();
		model.addAttribute(AppConstants.CONTACT_LITERAL, contact);
		logger.debug("****loadForm() method execution completed****");
		logger.info("**** Info Level loadForm() method executed successfully..!!****");
		return AppConstants.LOAD_FORM;
	}
	
	@PostMapping("/saveContact")
	public String saveBtnClick(Contact contact, RedirectAttributes attributes) {
		logger.debug("****saveBtnClick() method execution started****");
		Map<String, String> messages = appProps.getMessages();
		String txtMsg;
		if(contact.getContactId() == null) {
			txtMsg = messages.get(AppConstants.SAVE_SUCCESS);
		}else {
			txtMsg = messages.get(AppConstants.UPDATE_SUCCESS);
		}
		
		boolean isSaved = service.saveContact(contact);
		if(isSaved) {
			logger.info("Info Level Contact Saved Successfully into Database");
			attributes.addFlashAttribute(AppConstants.SUCCESS_MESSAGE, txtMsg);
		}else {
			logger.info("Info Level Contact Saved Failed");
			attributes.addFlashAttribute(AppConstants.ERROR_MESSAGE, messages.get(AppConstants.SAVE_FAIL));
		}
		logger.debug("****saveBtnClick() method execution ended****");
		return "redirect:/contactSave";
	}
	
	@GetMapping(value = "/contactSave")
	public String getloadForm(Model model) {
		Contact contact = new Contact();
		model.addAttribute(AppConstants.CONTACT_LITERAL,contact);
		return AppConstants.LOAD_FORM;
	}
	
	@GetMapping("/viewAllContacts")
	public String getAllContact(Model model) {
		logger.debug("*******ViewContacts execution started******");
		List<Contact> viewAllContacts = service.viewAllContacts();
		
		if(viewAllContacts.isEmpty()) {
			logger.info("********Info Level Contacts Are Not available in DB********");
		}
		
		model.addAttribute("allContacts", viewAllContacts);
		logger.debug("*******ViewContacts execution ended******");
		
		logger.info("*******Info Level ViewContacts execution completed successfully******");
		
		return "viewAllContact";
	}
	
	@GetMapping("/edit")
	public String handleEditClick(@RequestParam("cid") Integer cid, Model model) {
		
		logger.debug("****Execution started for Edit Click*****");
		
		Contact contactObj = service.getContactById(cid);
		model.addAttribute("contact", contactObj);
		logger.debug("****Execution completed for Edit Click*****");
		logger.info("****Info Level Execution completed successfully for Edit Click*****");
		return AppConstants.LOAD_FORM;
	}
	
	@GetMapping("/delete")
	public String handleDeleteClick(@RequestParam("cid") Integer cid) {
		logger.debug("****Execution started for Delete Click*****");
	    service.deleteContactById(cid);
	    logger.debug("****Execution completed for Delete Click*****");
	    logger.info("****Info Level Contact deleted successfully*****");
	    return "redirect:/viewAllContacts";
	}

}
