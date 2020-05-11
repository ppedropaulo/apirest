package com.tellma.apirest.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
public class ChatUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String username;
	
	private String password;
	
	@OneToMany(
	        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
	        orphanRemoval = true
	    )
 @JoinColumn(name = "chatuser_id")
 private Set<Messages> messages = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "Participants",
            joinColumns = { @JoinColumn(name = "userid") },
            inverseJoinColumns = { @JoinColumn(name = "chatid") })
    private Set<Chat> chats = new HashSet<>();
	
	public Set<Chat> getChats() {
		return chats;
	}

	public void setChats(Set<Chat> chats) {
		this.chats = chats;
	}

	

	public Set<Messages> getMessages() {
		return messages;
	}

	public void setMessages(Set<Messages> messages) {
		this.messages = messages;
	}


	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long userid) {
		this.id = userid;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}