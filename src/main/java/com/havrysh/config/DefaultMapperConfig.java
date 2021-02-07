package com.havrysh.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueCheckStrategy;

@MapperConfig(implementationPackage = "com.havrysh.server.converter.impl", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface DefaultMapperConfig {
}