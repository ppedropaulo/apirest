package com.tellma.apirest.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tellma.apirest.models.Chat;
import com.tellma.apirest.models.ChatUser;
import com.tellma.apirest.models.Messages;
import com.tellma.apirest.modelsdto.ChatDTO;
import com.tellma.apirest.repository.ChatRepository;
import com.tellma.apirest.repository.ChatUserRepository;
import com.tellma.apirest.repository.MessagesRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
	    public Map<String,String> Post(@Valid @RequestBody ChatUser chatuser)
	    {   
		 	HashMap<String,String> map = new HashMap<>();
		 	
		 	if(chatuser.getUsername() == "" || chatuserrepository.findByUsername(chatuser.getUsername())!= null) {
		 		map.put("sucessfull", "false");
		 		map.put("Error", "Usuario invalido");
		 		return map;
		 	}
		 	else {
		 		chatuserrepository.save(chatuser);
		 		map.put("sucessfull", "true");
		 		map.put("id", chatuser.getId().toString());
		 		map.put("username", chatuser.getUsername());
		 		return map;	
		 	}
	    
	    }
	 
	 @PostMapping(value = "/login")
	    public Map<String,String> Post1(@Valid @RequestBody ChatUser chatuser)
	    {   
		 
		 	ChatUser temp = chatuserrepository.findByUsername(chatuser.getUsername());
		 	HashMap<String,String> map = new HashMap<>();
		 	
		 	
		 	if(temp != null && temp.getPassword().equals(chatuser.getPassword()))  { 
		 		map.put("sucessfull", "true");
		 		map.put("id", temp.getId().toString());
		 		map.put("username", temp.getUsername());
		 		return map;	
		 	}
		 	else {
		 		map.put("sucessfull", "false");
		 		map.put("error", "usuario ou senha invalidos");
		 		return map;
		 	}
		 	
	    }
	 
	 @GetMapping(value = "/rooms")
	    public List<Map<String,String>> ListarChats(@RequestParam("user_id") Long user_id)
	    {   
		List<Map<String,String>> responses = new ArrayList<>();
		
		
		 
		 Optional<ChatUser> temp = chatuserrepository.findById(user_id);
		 Set<Chat> chatss = temp.get().getChats();
		 
		 for (Chat chat : chatss) {
			 Map<String,String> map = new HashMap<String,String>();
			 map.put("roomId", chat.getId().toString());
			 map.put("name", chat.getChatname());
			 responses.add(map)	;
	        }
		
		 return responses;
	    }

	 
	 @PostMapping(value = "/messages")
	 public void CriarMensagem(@RequestBody Messages mensagem) {
		 
		 messagesrepository.save(mensagem);
	 }
	 
	 
	 @GetMapping(value = "/messages")
	    public List<Map<String,String>> ListarMensagens(@RequestParam("room_id") Long user_id)
	    {   
		List<Map<String,String>> responses = new ArrayList<>();
		
		
		 
		 Optional<Chat> temp = chatrepository.findById(user_id);
		 Set<Messages> messages = temp.get().getMessages();
		 
		 for (Messages msg : messages) {
			 Map<String,String> map = new HashMap<String,String>();
			 map.put("id", msg.getId().toString());
			 map.put("text", msg.getText());
			 map.put("date", msg.getDate().toString());
			 map.put("username", msg.getChatuser().getUsername());
			 map.put("chat", msg.getChat().getId().toString());
			 responses.add(map)	;
	        }
		
		 return responses;
	    }
	 
	 @PostMapping(value = "/rooms")
	    public Map<String,String> CriarSala(@RequestBody ChatDTO chatdto)
	    {   
		 Chat chat = new Chat();
		 chat.setChatname(chatdto.getChatname());
		 Set<ChatUser> users = new HashSet<ChatUser>();
		 for(String participante: chatdto.getParts()) {
			 users.add(chatuserrepository.findByUsername(participante));
		 }
		
		 chat.getUsers().addAll(users);
		 chatrepository.save(chat);
		 
		 HashMap<String,String> map = new HashMap<>();
		 map.put("sucessfull","true");
		 return map;
		 
	    }
	 
	 
	 
	 	
	 

	 
	 
}
