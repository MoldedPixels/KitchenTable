package com.project2.kitchentable.controllers;

import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.project2.kitchentable.aspects.Admin;
import com.project2.kitchentable.aspects.LoggedIn;
import com.project2.kitchentable.beans.Notes;
import com.project2.kitchentable.services.NoteService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/notes")
public class NotesController {

	@Autowired
	private NoteService noteService;

	private static Logger log = LogManager.getLogger(NotesController.class);

	@LoggedIn
	@PostMapping("add")
	public Mono<Notes> addNote(ServerWebExchange exchange, @RequestBody Notes n) {
		log.trace("Adding a note to recipe: " + n.getRecipeId());
		return noteService.addNotes(n);
	}

	@LoggedIn
	@GetMapping(value = "getall/{recipeid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Notes> getNotesByRecipe(ServerWebExchange exchange, @PathVariable("recipeid") String recipeID) {
		return noteService.getNotes(UUID.fromString(recipeID));
	}

	@Admin
	@PutMapping("{noteid}")
	public Mono<Notes> updateNote(ServerWebExchange exchange, @PathVariable("noteid") String noteID,
			@RequestBody Notes n) {
		return noteService.updateNotes(n);
	}

	@Admin
	@DeleteMapping("{recipeid}/{userid}")
	public Mono<Void> removeNote(ServerWebExchange exchange, @PathVariable("recipeid") String recipeID,
			@PathVariable("userid") String userID) {
		return noteService.removeNotes(UUID.fromString(recipeID), UUID.fromString(userID));
	}
}
