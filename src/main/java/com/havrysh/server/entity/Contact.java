package com.havrysh.server.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	protected Date created;
	
	@UpdateTimestamp
	protected Date updated;
}