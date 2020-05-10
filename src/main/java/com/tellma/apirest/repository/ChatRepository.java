package com.tellma.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tellma.apirest.models.Chat;

public interface ChatRepository extends JpaRepository<Chat,Long> {

}
