package com.project2.kitchentable.services;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.kitchentable.beans.Notes;
import com.project2.kitchentable.data.ReactiveNoteRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NoteServiceImpl {

	private static Logger log = LogManager.getLogger(NoteServiceImpl.class);
	
	@Autowired
	private ReactiveNoteRepo noteRepo;
	
	public Mono<Notes> addNotes(Notes n){
		return noteRepo.insert(n);
	}
	
	public Flux<Notes> getNotes() {
		return noteRepo.findAll();
	}
	
	public Mono<Notes> updateNotes(Notes n){
		return noteRepo.save(n);
	}
	
	public Mono<Notes> getNotesById(UUID id){
		String notesId = id.toString();
		
		return noteRepo.findById(notesId);
	}
	
	public Mono<Void> removeNotes(Notes n){
		return noteRepo.delete(n);
	}
	
	
	
}
