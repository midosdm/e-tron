package com.etron.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<Subscription>> getAllSubscriptions() {
		return subscriptionService.getAllSubscriptions();
	}

	@GetMapping(path = "{subscriptionId}")
	public ResponseEntity<Subscription> getSubscription(@PathVariable("subscriptionId") Long subscriptionId) {
		return subscriptionService.getById(subscriptionId);
	}

	@PostMapping
	public ResponseEntity<Subscription> createSubscription(@Valid @RequestBody Subscription subscription) {
		return subscriptionService.createSubscription(subscription);
	}

	@PutMapping(path = "{subscriptionId}")
	public ResponseEntity<Subscription> updateSubscription(@PathVariable("subscriptionId") Long subscriptionId,
			@Valid @RequestBody Subscription subscription) {
		return subscriptionService.updateSubscription(subscriptionId, subscription);
	}

	@DeleteMapping(path = "{subscriptionId")
	public ResponseEntity<HttpStatus> deleteSubscription(@PathVariable("subscriptionId") Long subscriptionId) {
		return subscriptionService.deleteSubscription(subscriptionId);
	}

	@DeleteMapping()
	public ResponseEntity<HttpStatus> deleteAllSubscriptions() {
		return subscriptionService.deleteAllSubscriptions();
	}

}
