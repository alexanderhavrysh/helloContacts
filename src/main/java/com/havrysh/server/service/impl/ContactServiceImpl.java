package com.havrysh.server.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.havrysh.server.converter.ContactConverter;
import com.havrysh.server.dto.ContactDto;
import com.havrysh.server.entity.Contact;
import com.havrysh.server.exception.runtime.NotFoundException;
import com.havrysh.server.exception.runtime.ServerException;
import com.havrysh.server.repository.ContactRepository;
import com.havrysh.server.service.ContactService;
import com.havrysh.server.utils.Constants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = Constants.CONTACTS_CACHE)
public class ContactServiceImpl implements ContactService {
	private final ContactRepository contactRepository;
	private final ContactConverter contactConverter;
	
	@Override
	@Cacheable
	public List<ContactDto> getContactsByPattern(String regex) {
		try {
			Pattern pattern = Pattern.compile(regex);
			
			return contactRepository.findAll()
					.stream()
					.filter(contact -> !pattern.matcher(contact.getName()).matches())
					.map(contactConverter::fromEntity)
					.collect(Collectors.toList());
		} catch(Exception ex) {
			throw new ServerException(Contact.class, null, String.format("Unable to get contact by pattern [%s]", regex), ex);
		}
	}
	
	@Override
	@CachePut(key = "#result.id")
	public ContactDto save(ContactDto contactDto) {
		try {
			return Optional.ofNullable(contactDto)
					.map(contactConverter::toEntity)
					.map(contactRepository::save)
					.map(contactConverter::fromEntity)
					.orElseThrow(NotFoundException::new);
		} catch(NotFoundException nfe) {
			throw nfe;
		} catch(Exception ex) {
			throw new ServerException(Contact.class, null, "Unable to save contact", ex);
		}
	}
	
	@Override
	@Cacheable
	public ContactDto get(Long id) {
		try {
			return contactRepository.findById(id).map(contactConverter::fromEntity).orElseThrow(NotFoundException::new);
		} catch(NotFoundException nfe) {
			throw nfe;
		} catch(Exception ex) {
			throw new ServerException(Contact.class, id, "Unable to get contact by id", ex);
		}
	}
	
	@Override
	@CacheEvict(allEntries = true)
	public void delete(Long id) {
		try {
			contactRepository.deleteById(id);
		} catch(Exception ex) {
			throw new ServerException(Contact.class, id, "Unable to get contact by id", ex);
		}
	}
}