package com.tellma.apirest.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tellma.apirest.models.Chat;
import com.tellma.apirest.models.ChatUser;
import com.tellma.apirest.models.Messages;
import com.tellma.apirest.modelsdto.ChatDTO;
import com.tellma.apirest.modelsdto.CompleteChat;
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
import java.math.BigInteger;
import java.sql.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin
@RestController
@RequestMapping(value="")
public class Controller {
	
	

	@Autowired
	ChatRepository chatrepository;
	
	@Autowired
	ChatUserRepository chatuserrepository;
	
	@Autowired
	MessagesRepository messagesrepository;
	
	
	 
	 @CrossOrigin
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
	 @CrossOrigin
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
	 @CrossOrigin
	 @GetMapping(value = "/rooms")
	    public List<Map<String,String>> ListarChats(@RequestParam("user_id") Long user_id)
	    {   
		List<Map<String,String>> responses = new ArrayList<>();
		
		 
		
		 List<Object[]> msgs = chatrepository.getLastMessage(user_id);	
		 List<CompleteChat> result = new ArrayList<CompleteChat>();
		 
		 for(Object[] obj : msgs) {
			 CompleteChat data = new CompleteChat();
			 if(obj[2]==null) {
				 data.setChatname((String)obj[0]);
				 data.setChatid((BigInteger)obj[1]);
			 }
			 else {
				 data.setIsmessage("true");
				 data.setChatname((String)obj[0]);
				 data.setChatid((BigInteger)obj[1]);
				 data.setText((String)obj[2]);
				 data.setDate((Timestamp)obj[3]);
				 data.setUsername((String)obj[4]); 
			 }
			 
			 result.add(data);
		 }
		 
		 
		 
		 for (CompleteChat chat : result) {
			 Map<String,String> map = new HashMap<String,String>();
			 map.put("roomId", chat.getChatid().toString());
			 map.put("name", chat.getChatname());
			 map.put("lastmessage",chat.getIsmessage());
			 map.put("username", chat.getUsername());
			 map.put("timestamp", chat.getDate().toString());
			 map.put("text", chat.getText());
			 
			 responses.add(map)	;
	        }
		
		 
		return responses;
	    }

	 @CrossOrigin
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
	 
	 @CrossOrigin
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
	 
	 @CrossOrigin
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
	 
	 
	 @CrossOrigin
	 @GetMapping(value = "/users")
	    public List<Map<String,String>> ListarUsers(@RequestParam("room_id") Long room_id)
	    {   
		List<Map<String,String>> responses = new ArrayList<>();
		
		
		 
		 Optional<Chat> temp = chatrepository.findById(room_id);
		 Set<ChatUser> userss = temp.get().getUsers();
		 
		 for (ChatUser users : userss) {
			 Map<String,String> map = new HashMap<String,String>();
			 
			 map.put("name", users.getUsername());
			 map.put("userId", users.getId().toString());
			 
			 responses.add(map)	;
	        }
		
		 return responses;
	    }
	 
	 
	 
	 
	 

	 
	 
}
