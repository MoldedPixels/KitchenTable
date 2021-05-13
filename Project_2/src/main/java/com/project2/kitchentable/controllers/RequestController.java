package com.project2.kitchentable.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.project2.kitchentable.beans.Requests;
import com.project2.kitchentable.services.RequestService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/request")
public class RequestController {

	private RequestService requestService;

	@Autowired
	public void setRequestService(RequestService requestService) {
		this.requestService = requestService;
	}

	@PostMapping("/new")
	public Mono<ResponseEntity<Requests>> addKitchen(@RequestBody Requests q) {
		System.out.println("Making a new request");
		q.setRequestId(Uuids.timeBased());
		if (q.getRecipeId().equals(null)) {
			q.setRecipeId(Uuids.timeBased());
		}
		return requestService.addRequest(q).map(request -> ResponseEntity.status(201).body(request))
				.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(q)));
	}

}
