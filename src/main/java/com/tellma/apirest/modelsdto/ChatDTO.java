package com.tellma.apirest.modelsdto;

import java.util.List;

public class ChatDTO {
	
	private String chatname;
	
    private List<String> parts;

	public String getChatname() {
		return chatname;
	}

	public void setChatname(String chatname) {
		this.chatname = chatname;
	}

	public List<String> getParts() {
		return parts;
	}

	public void setParts(List<String> parts) {
		this.parts = parts;
	}

}
