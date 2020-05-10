package com.tellma.apirest.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


@Entity
public class Messages implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	private String body;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}