package com.project2.kitchentable.services;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.kitchentable.beans.Requests;
import com.project2.kitchentable.data.ReactiveRequestRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestServiceImpl implements RequestService {
	private static Logger log = LogManager.getLogger(RequestServiceImpl.class);
	
	@Autowired
	private ReactiveRequestRepo requestRepo;
	
	@Override
	public Mono<Requests> addRequest(Requests r){
		return requestRepo.insert(r);
	}
	
	@Override
	public Flux<Requests> getRequests() {
		return requestRepo.findAll();
	}
	
	@Override
	public Mono<Requests> updateRequest(Requests r){
		return requestRepo.save(r);
	}
	
	@Override
	public Mono<Requests> getRequestById(UUID id){
		String requestId = id.toString();
		
		return requestRepo.findById(requestId);
	}
	
	@Override
	public Mono<Void> removeRequest(Requests r){
		return requestRepo.delete(r);
	}
}