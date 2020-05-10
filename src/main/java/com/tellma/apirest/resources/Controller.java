package com.tellma.apirest.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tellma.apirest.models.Chat;
import com.tellma.apirest.models.ChatUser;
import com.tellma.apirest.repository.ChatRepository;
import com.tellma.apirest.repository.ChatUserRepository;
import com.tellma.apirest.repository.MessagesRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value="")
public class Controller {
	
	@Autowired
	ChatRepository chatrepository;
	
	@Autowired
	ChatUserRepository chatuserrepository;
	
	@Autowired
	MessagesRepository messagesrepository;
	
	
	 @PostMapping(value = "/signup")
	    public ChatUser Post(@Valid @RequestBody ChatUser chatuser)
	    {
	        return chatuserrepository.save(chatuser);
	    }
	 
	
	
	

}
