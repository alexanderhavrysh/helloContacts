package com.havrysh.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Contact model")
public class ContactDto {
	@ApiModelProperty("id of contact")
	private Long id;
	
	@ApiModelProperty("name of contact")
	private String name;
}