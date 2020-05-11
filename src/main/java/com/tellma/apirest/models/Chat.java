package com.tellma.apirest.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;



@Entity
public class Chat implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String chatname;
	
	
	 @OneToMany(
		        cascade = {CascadeType.PERSIST,CascadeType.MERGE},
		        orphanRemoval = true
		    )
	 @JoinColumn(name = "chat_id")
	 private Set<Messages> messages = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "chats")
    private Set<ChatUser> users = new HashSet<>();

	
	public Set<Messages> getMessages() {
		return messages;
	}

	public void setMessages(Set<Messages> messages) {
		this.messages = messages;
	}

	public Set<ChatUser> getUsers() {
		return users;
	}

	public void setUsers(Set<ChatUser> users) {
		this.users = users;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long chatid) {
		this.id = chatid;
	}



	public String getChatname() {
		return chatname;
	}

	public void setChatname(String chatname) {
		this.chatname = chatname;
	}
	
	

	
	
	
	

}
