package com.tellma.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tellma.apirest.models.ChatUser;

public interface ChatUserRepository extends JpaRepository<ChatUser,Long> {

}
