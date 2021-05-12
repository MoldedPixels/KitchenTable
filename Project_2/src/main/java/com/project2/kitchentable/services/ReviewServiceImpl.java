package com.project2.kitchentable.services;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.kitchentable.beans.Reviews;
import com.project2.kitchentable.data.ReactiveReviewRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReviewServiceImpl implements ReviewService {
	private static Logger log = LogManager.getLogger(ReviewServiceImpl.class);
	
	@Autowired
	private ReactiveReviewRepo reviewRepo;
	
	@Override
	public Mono<Reviews> addReview(Reviews r){
		return reviewRepo.insert(r);
	}
	
	@Override
	public Flux<Reviews> getReviews() {
		return reviewRepo.findAll();
	}
	
	@Override
	public Mono<Reviews> updateReview(Reviews r){
		return reviewRepo.save(r);
	}
	
	@Override
	public Mono<Reviews> getReviewById(UUID id){
		String reviewId = id.toString();
		
		return reviewRepo.findById(reviewId);
	}
	
	@Override
	public Mono<Void> removeReview(Reviews r){
		return reviewRepo.delete(r);
	}
	
}
