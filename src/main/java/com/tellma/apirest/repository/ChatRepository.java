package com.tellma.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tellma.apirest.models.Chat;


public interface ChatRepository extends JpaRepository<Chat,Long> {

	Optional<Chat> findById(Long id);
}
