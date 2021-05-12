package com.project2.kitchentable.controllers;

import java.util.List;
import java.util.UUID;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.project2.kitchentable.beans.Kitchen;
import com.project2.kitchentable.services.KitchenService;
import com.project2.kitchentable.services.RecipeService;
import com.project2.kitchentable.services.ReviewService;
import com.project2.kitchentable.beans.Recipe;
import com.project2.kitchentable.beans.Reviews;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/kitchen")
public class KitchenController {

	private KitchenService kitchenService;
	private RecipeService recipeService;
	private ReviewService reviewService;

	@Autowired
	public void setKitchenService(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	// @Autowired
//	public void setRecipeService(RecipeService recipeService) {
//		this.recipeService = recipeService;
//	}

	@PostMapping("/new")
	public Mono<ResponseEntity<Kitchen>> addKitchen(@RequestBody Kitchen k) {
		System.out.println("Making a new kitchen");
		k.setId(Uuids.timeBased());
		k.setHeadUser(Uuids.timeBased());
		k.setFamilyID(Uuids.timeBased());
		return kitchenService.addKitchen(k).map(kitchen -> ResponseEntity.status(201).body(kitchen))
				.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(k)));
	}

	@PostMapping("/addToList")
	public Mono<ResponseEntity<Kitchen>> addToList(@RequestParam(name = "list", required = false) String listname,
			@RequestParam(name = "kitchen", required = false) UUID kID,
			@RequestParam(name = "ingredient", required = false) UUID iID,
			@RequestParam(name = "amount", required = false) Double amt) {
		System.out.println("Adding to " + listname);
		Kitchen k = kitchenService.getKitchenByID(kID).block();
		return kitchenService.addFood(listname, k, iID, amt).map(kitchen -> ResponseEntity.status(201).body(kitchen))
				.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(k)));
	}

	@GetMapping(value = "/kitchen/removeFood")
	public Mono<ResponseEntity<Kitchen>> removeFood(@RequestParam(name = "list", required = false) String listname,
			@RequestParam(name = "kitchen", required = false) UUID kID,
			@RequestParam(name = "ingredient", required = false) UUID iID,
			@RequestParam(name = "amount", required = false) Double amt) throws Exception {
		Kitchen k = kitchenService.getKitchenByID(kID).block();
		return kitchenService.removeFood(listname, k, iID, amt).map(kitchen -> ResponseEntity.status(201).body(kitchen))
				.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(k)));

	}

	@GetMapping(value = "/kitchen/cook")
	public Mono<ResponseEntity<String>> cook(@RequestParam(name = "recipe", required = false) UUID recipe,
			@RequestParam(name = "kitchen", required = false) UUID kID, 
			@RequestParam(name = "review", required = false) String reviewBody,
			@RequestParam(name = "score", required = false) Double score,
			@RequestParam(name = "images", required = false) MultipartFile images) throws Exception {
		Kitchen k = kitchenService.getKitchenByID(kID).block();
		Recipe r = recipeService.getRecipeById(recipe).block();
		if((reviewBody != null) && (score != null)) {
			Reviews rev = new Reviews(Uuids.timeBased(), Uuids.timeBased() /* This param will be changed to the logged in user's UUID */, recipe, score, reviewBody);
			Mono<ResponseEntity<String>> response = kitchenService.cook(r, k).map(kitchen -> ResponseEntity.status(201).body(kitchen.toString())) // Try to cook
					.onErrorResume(error -> { return Mono.just(ResponseEntity.badRequest().body(error.toString())); }); // If error, return error message to Mono
			response = reviewService.addReview(rev).map(review -> ResponseEntity.status(201).body(review.toString())) // Otherwise, proceed to try to add review
					.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(error.toString()))); // If error, assign error message to response
			return response;
		}
		else {
			return kitchenService.cook(r, k).map(kitchen -> ResponseEntity.status(201).body(kitchen.toString()))
					.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(error.toString())));
		}


	}
	
	@GetMapping(value = "reviews/{recipe}")
	public Publisher<ResponseEntity<Kitchen>> viewReviews(@PathVariable("recipe") UUID recipe) {
		reviewService.getReviewsByRecipeId(recipe); // TODO
		return null;
		
	}

}
