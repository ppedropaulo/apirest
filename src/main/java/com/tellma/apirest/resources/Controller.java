package com.tellma.apirest.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tellma.apirest.models.Chat;
import com.tellma.apirest.models.ChatUser;
import com.tellma.apirest.models.Messages;
import com.tellma.apirest.modelsdto.ChatDTO;
import com.tellma.apirest.modelsdto.MessageDTO;
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
	 public List<Map<String,String>> CriarMensagem(@RequestBody MessageDTO msg) {
		 List<Map<String,String>> responses = new ArrayList<>();
		 
		 Messages mensagem = new Messages();
		 mensagem.setChat(chatrepository.findById(msg.getChatId()).orElse(null));
		 mensagem.setChatuser(chatuserrepository.findById(msg.getUserId()).orElse(null));
		 mensagem.setText(msg.getText());
		 
		 messagesrepository.save(mensagem);
		 
		 Map<String,String> map = new HashMap<String,String>();
		 map.put("id", msg.getUserId().toString());
		 map.put("text", msg.getText());
		 map.put("date", messagesrepository.save(mensagem).getDate().toString());
		 map.put("username", messagesrepository.save(mensagem).getChatuser().getUsername());
		 map.put("userId", messagesrepository.save(mensagem).getChatuser().getId().toString());
		 map.put("chatId", messagesrepository.save(mensagem).getChat().getId().toString());
		 responses.add(map)	;
		 
		 
		 return responses;
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
		 chat.setChatname(chatdto.getName());
		 Set<ChatUser> users = new HashSet<ChatUser>();
		 for(String participante: chatdto.getUsers()) {
			 users.add(chatuserrepository.findByUsername(participante));
			 ChatUser user = chatuserrepository.findByUsername(participante);
			 chat.getUsers().add(user);
		 }
		
		
		 chatrepository.save(chat);
		 
		 HashMap<String,String> map = new HashMap<>();
		 map.put("sucessfull","true");
		 return map;
		 
	    }
	 
	 
	 
	 	
	 

	 
	 
}
