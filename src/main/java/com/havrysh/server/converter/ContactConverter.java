package com.havrysh.server.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.havrysh.config.DefaultMapperConfig;
import com.havrysh.server.dto.ContactDto;
import com.havrysh.server.entity.Contact;

@Mapper(config = DefaultMapperConfig.class)
public interface ContactConverter {
	@Mapping(target = "updated", ignore = true)
	@Mapping(target = "created", ignore = true)
	Contact toEntity(ContactDto contactDto);
	
	ContactDto fromEntity(Contact contact);
}