package com.tellma.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tellma.apirest.models.Chat;
import com.tellma.apirest.models.Messages;
import com.tellma.apirest.modelsdto.MessageDTO;


public interface ChatRepository extends JpaRepository<Chat,Long> {
	
	@Query(value = "SELECT c.chatname,p.chatid,m.text,m.date,cu.username\r\n" + 
			"FROM chat c\r\n" + 
			"LEFT JOIN participants p\r\n" + 
			"ON c.id = p.chatid\r\n" + 
			"LEFT JOIN messages m\r\n" + 
			"ON c.id = m.chat_id\r\n" + 
			"LEFT JOIN chat_user cu\r\n" + 
			"ON m.chatuser_id = cu.id\r\n" + 
			"WHERE (m.date = (\r\n" + 
			"        SELECT max(date)\r\n" + 
			"        FROM messages m1\r\n" + 
			"        WHERE c.id = m1.chat_id\r\n" + 
			"     ) OR m.date is NULL)\r\n" + 
			"AND p.userid = :u",
			nativeQuery=true)
		List<Object[]> getLastMessage(@Param("u") Long u); 

	Optional<Chat> findById(Long id);
}
