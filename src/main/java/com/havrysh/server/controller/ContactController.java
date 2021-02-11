package com.havrysh.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.havrysh.server.dto.ContactDto;
import com.havrysh.server.service.ContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hello")
@Api(tags = "Contact API")
public class ContactController {
	private final ContactService contactService;
	
	@PostMapping("/contact")
	@ApiOperation("Create or update contact")
	public ContactDto save(@RequestBody ContactDto contactDto) {
		return contactService.save(contactDto);
	}
	
	@DeleteMapping("/contact/{id}")
	@ApiOperation("Delete contact by id")
	public void delete(@PathVariable Long id) {
		contactService.delete(id);
	}
	
	@GetMapping("/contact/{id}")
	@ApiOperation("Get contact by id")
	public ContactDto get(@PathVariable Long id) {
		return contactService.get(id);
	}
	
	@GetMapping("/contacts")
	@ApiOperation("Get pageable contacts by string regex")
	public List<ContactDto> getContactsByPattern(@RequestParam("nameFilter") String regex) {
		return contactService.getContactsByPattern(regex);
	}
}