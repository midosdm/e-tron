package com.etron.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.etron.models.Subscription;
import com.etron.services.SubscriptionService;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {
	private final ModelMapper modelMapper;

	private final SubscriptionService subscriptionService;

	@Autowired
	public SubscriptionController(ModelMapper modelMapper, SubscriptionService subscriptionService) {
		this.modelMapper = modelMapper;
		this.subscriptionService = subscriptionService;
	}

	@GetMapping
	public ResponseEntity<?> getAllSubscriptions() {
		return subscriptionService.getAllSubscriptions();
	}

	@GetMapping(path = "{subscriptionId}")
	public ResponseEntity<?> getSubscription(@PathVariable("subscriptionId") Long subscriptionId) {
		return subscriptionService.getById(subscriptionId);
	}

	@GetMapping(path="/getByType")
	public ResponseEntity<?> getSubscriptionByType(@RequestParam("subscriptionType") String type) {
		return subscriptionService.getByType(type);
	}

	@PostMapping
	public ResponseEntity<?> createSubscription(@Valid @RequestBody Subscription subscription) {
		return subscriptionService.createSubscription(subscription);
	}

	@PutMapping(path = "{subscriptionId}")
	public ResponseEntity<?> updateSubscription(@PathVariable("subscriptionId") Long subscriptionId,
			@Valid @RequestBody Subscription subscription) {
		return subscriptionService.updateSubscription(subscriptionId, subscription);
	}

	@DeleteMapping(path = "{subscriptionId}")
	public ResponseEntity<?> deleteSubscription(@PathVariable("subscriptionId") Long subscriptionId) {
		return subscriptionService.deleteSubscription(subscriptionId);
	}

	@DeleteMapping()
	public ResponseEntity<?> deleteAllSubscriptions() {
		return subscriptionService.deleteAllSubscriptions();
	}

}
