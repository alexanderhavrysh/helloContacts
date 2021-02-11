package com.havrysh.server.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.havrysh.server.converter.ContactConverter;
import com.havrysh.server.converter.impl.ContactConverterImpl;
import com.havrysh.server.dto.ContactDto;
import com.havrysh.server.entity.Contact;
import com.havrysh.server.repository.ContactRepository;
import com.havrysh.server.service.ContactService;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {
	@Mock ContactRepository contactRepository;
	
	private final ContactConverter contactConverter = new ContactConverterImpl();
	private final List<Contact> contactList = new ArrayList<>();
	private final Long id = 1L;
	
	private final Contact contactAnton = new Contact(id, "Anton");
	private final Contact contactIgor = new Contact(2L, "Igor");
	private final Contact contactVova = new Contact(5L, "Vova");
	
	private ContactService contactService;
	
	@Before
	public void setUp() {
		contactList.add(contactAnton);
		contactList.add(contactIgor);
		contactList.add(contactVova);
		contactList.add(new Contact(3L, "Alexander"));
		contactList.add(new Contact(4L, "Adolf"));
		
		when(contactRepository.findAll()).thenReturn(contactList);
		when(contactRepository.save(contactAnton)).thenReturn(contactAnton);
		when(contactRepository.findById(id)).thenReturn(Optional.of(contactAnton));
		contactService = new ContactServiceImpl(contactRepository, contactConverter);
	}
	
	@Test
	public void getContactsByPattern() {
		String regex = "^A.*$";
		List<ContactDto> contactListByPattern = contactService.getContactsByPattern(regex);
		
		ContactDto contactDtoIgor = contactListByPattern.get(0);
		ContactDto contactDtoVova = contactListByPattern.get(1);
		
		assertEquals(2, contactListByPattern.size());
		assertEquals(contactIgor.getId(), contactDtoIgor.getId());
		assertEquals(contactIgor.getName(), contactDtoIgor.getName());
		assertEquals(contactVova.getId(), contactDtoVova.getId());
		assertEquals(contactVova.getName(), contactDtoVova.getName());
	}
	
	@Test
	public void save() {
		ContactDto contactDto = new ContactDto();
		contactDto.setId(contactAnton.getId());
		contactDto.setName(contactAnton.getName());
		
		ContactDto currentContactDto = contactService.save(contactDto);
		
		assertEquals(contactAnton.getId(), currentContactDto.getId());
		assertEquals(contactAnton.getName(), currentContactDto.getName());
	}
	
	@Test
	public void get() {
		ContactDto contactDto = contactService.get(id);
		
		verify(contactRepository).findById(id);
		assertEquals(contactAnton.getId(), contactDto.getId());
		assertEquals(contactAnton.getName(), contactDto.getName());
	}
	
	@Test
	public void delete() {
		contactService.delete(id);
		
		verify(contactRepository).deleteById(id);
	}
}