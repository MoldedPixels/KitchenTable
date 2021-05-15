package com.project2.kitchentable.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.kitchentable.services.ReviewService;
import com.project2.kitchentable.beans.Reviews;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

	private ReviewService reviewService;
	
	@Autowired
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@GetMapping(value = "/{recipe}")
	public Flux<ResponseEntity<Reviews>> viewReviews(@PathVariable("recipe") UUID recipe) {
		return reviewService.getReviewsByRecipeId(recipe).map(reviews -> ResponseEntity.status(201).body(reviews));
		
	}

}
