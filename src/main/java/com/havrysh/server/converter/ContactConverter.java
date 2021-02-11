package com.havrysh.server.converter;

import org.mapstruct.Mapper;

import com.havrysh.config.DefaultMapperConfig;
import com.havrysh.server.dto.ContactDto;
import com.havrysh.server.entity.Contact;

@Mapper(config = DefaultMapperConfig.class)
public interface ContactConverter {
	Contact toEntity(ContactDto contactDto);
	
	ContactDto fromEntity(Contact contact);
}