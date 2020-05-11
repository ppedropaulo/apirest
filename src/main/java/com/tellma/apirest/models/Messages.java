package com.tellma.apirest.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


@Entity
public class Messages implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private String text;
	
	Timestamp date = new Timestamp(System.currentTimeMillis());
	
	@ManyToOne(fetch = FetchType.LAZY)
    private ChatUser chatuser;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;
	
	
	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public ChatUser getChatuser() {
		return chatuser;
	}

	public void setChatuser(ChatUser chatuser) {
		this.chatuser = chatuser;
	}


	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp timestamp) {
		this.date = timestamp;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}

	public void setText(String body) {
		this.text = body;
	}
	
}