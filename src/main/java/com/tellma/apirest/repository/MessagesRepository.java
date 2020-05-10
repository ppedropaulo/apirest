package com.tellma.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tellma.apirest.models.Messages;

public interface MessagesRepository extends JpaRepository<Messages,Long> {

}
