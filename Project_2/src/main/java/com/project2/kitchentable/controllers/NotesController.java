package com.project2.kitchentable.controllers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.project2.kitchentable.beans.Notes;
import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.services.NoteService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/notes")
public class NotesController {

	@Autowired
	private NoteService noteService;
	@Autowired
	private AuthController authorize;
	private static Logger log = LogManager.getLogger(RecipeController.class);
	
	@PostMapping("/add")
	public Mono<Notes> addNote(ServerWebExchange exchange, @RequestBody Notes n) {
		User user = authorize.UserAuth(exchange);
		
		if(user != null && user.cooked(n.getRecipeId(), user)) {
			return noteService.addNotes(n);
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return null;
	}
	
	@GetMapping(value="/getall/{recipeid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Notes> getNotesByRecipe(ServerWebExchange exchange, @PathVariable("recipeid") String recipeID) {
		User u = authorize.UserAuth(exchange);
		
		if(u != null) {
			return noteService.getNotesById(UUID.fromString(recipeID));
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return null;
	}
	
	@PutMapping("/{noteid}")
	public Mono<Notes> updateNote(ServerWebExchange exchange, @PathVariable("noteid") String noteID, @RequestBody Notes n){
		User user = authorize.UserAuth(exchange);
		
		if(user != null && n.getUserId() == user.getUserID()) {
			return noteService.updateNotes(n);
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return null;
	}
	
	@DeleteMapping("/{noteid}")
	public Mono<Void> removeNote(ServerWebExchange exchange, @PathVariable("noteid") String noteID){
		User user = authorize.UserAuth(exchange);
		Notes n = null;
		try {
			n = noteService.getNotesById(UUID.fromString(noteID)).block(Duration.of(1000, ChronoUnit.MILLIS));
		}catch(Exception e) {
		for (StackTraceElement st : e.getStackTrace())
			log.debug(st.toString());
		}
		
		if(user != null && (user.getUserType() == 3 || n.getUserId() == user.getUserID())) {
				noteService.removeNotes(n);
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return null;
	}
}
