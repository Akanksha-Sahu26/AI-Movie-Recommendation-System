package com.example.controllers;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.MovieRecommendationDto;

@RestController
@RequestMapping("/api")
public class ChatController {
	private ChatClient chatClient;
	
	public ChatController(ChatClient.Builder builder)
	{
		this.chatClient=builder.build();
	}
	@PostMapping("/recommend")
	public ResponseEntity<?> chat(@RequestParam("type") String type,@RequestParam("lang") String lang )
	{
		try {
			PromptTemplate template=new PromptTemplate("Recommend me a movie of type {type} and in {lang} language. Return only json matching the schema");
			Map<String, Object> data=Map.of("type",type,"lang",lang);
			Prompt prompt=template.create(data);
			MovieRecommendationDto movie=chatClient
					                     .prompt()
					                     .system("You are an api that returns a single valid JSON object")
					                     .user(prompt.getContents())
					                     .call()
					                     .entity(MovieRecommendationDto.class);
			return ResponseEntity.ok(movie);
		}catch(Exception ex)
		{
			System.out.println("AI provider error"+ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("AI provider error "+ex.getMessage());
		}
		
	}
	
	
}

