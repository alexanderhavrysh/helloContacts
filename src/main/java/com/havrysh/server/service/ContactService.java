package com.havrysh.server.service;

import java.util.List;

import com.havrysh.server.dto.ContactDto;

public interface ContactService {
	/**
	 * Get list of contacts by regex
	 * @param regex name`s regex
	 * @return list of contacts
	 */
	List<ContactDto> getContactsByPattern(String regex);
	
	/**
	 * Create or update the contact
	 * @param contactDto new contact data
	 * @return saved contact
	 */
	ContactDto save(ContactDto contactDto);
	
	/**
	 * Get the contact by id
	 * @param id id of the contact
	 * @return contact
	 */
	ContactDto get(Long id);
	
	/**
	 * Delete the contact by id
	 * @param id id of the contact
	 */
	void delete(Long id);
}
