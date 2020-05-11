package com.tellma.apirest.modelsdto;

import java.util.List;

public class ChatDTO {
	
	private String name;
	
    private List<String> users;

	public String getName() {
		return name;
	}

	public void setName(String chatname) {
		this.name = chatname;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> parts) {
		this.users = parts;
	}

}
