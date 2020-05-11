package com.tellma.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tellma.apirest.models.ChatUser;

public interface ChatUserRepository extends JpaRepository<ChatUser,Long> {

	ChatUser findByUsername(String username);
	
	Optional<ChatUser> findById(Long id);
	
	
}
