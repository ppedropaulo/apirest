package com.tellma.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tellma.apirest.models.Chat;
import com.tellma.apirest.models.Messages;

public interface MessagesRepository extends JpaRepository<Messages,Long> {
	
	List<Messages> findByChatOrderById(Chat chat);
}
