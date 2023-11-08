package com.guilhermekumagai.workshopmongo.dto;

import com.guilhermekumagai.workshopmongo.domain.User;

public class AuthorDTO {
	private String id;
	private String name;

	public AuthorDTO() {
	}
	
	public AuthorDTO(User obj) {
		setId(obj.getId());
		setName(obj.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
