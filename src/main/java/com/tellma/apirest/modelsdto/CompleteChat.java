package com.tellma.apirest.modelsdto;

import java.math.BigInteger;
import java.sql.Timestamp;

public class CompleteChat {
	
	public CompleteChat() {
		this.ismessage="false";
		this.text="";
		this.date= new Timestamp(System.currentTimeMillis());
		this.username="";
	}
	
	private String ismessage;
	
	private String text;
	
	private String username;
	
	private String chatname;
	
	private BigInteger chatid;
	
	Timestamp date;
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username=username;
	}

	public BigInteger getChatid() {
		return chatid;
	}

	public void setChatid(BigInteger chatid) {
		this.chatid = chatid;
	}

	public String getChatname() {
		return chatname;
	}

	public void setChatname(String chatname) {
		this.chatname = chatname;
	}

	public String getIsmessage() {
		return ismessage;
	}

	public void setIsmessage(String ismessage) {
		this.ismessage = ismessage;
	}


}



