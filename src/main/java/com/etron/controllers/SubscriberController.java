package com.etron.controllers;

<<<<<<< HEAD
import java.util.List;
import java.util.stream.Collectors;

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

import com.etron.dto.SubscriberDto;
import com.etron.models.Subscriber;
import com.etron.services.SubscriberService;

@RestController
@RequestMapping(path = "/api/v1/subscribers")

public class SubscriberController {

	@Autowired
	private SubscriberService subscriberService;

	private ModelMapper modelmapper;

	private SubscriberDto convertToDto(Subscriber subscriber) {
		return modelmapper.map(subscriber, SubscriberDto.class);
	}

	private Subscriber convertToEntity(SubscriberDto subscriberDto) {
		return modelmapper.map(subscriberDto, Subscriber.class);
	}

	private Subscriber convertToEntity(SubscriberWithoutPasswordDto subscriberDto) {
		return modelmapper.map(subscriberDto, Subscriber.class);
	}

	@GetMapping
	public List<SubscriberDto> getAllSubscribers() {
		List<Subscriber> subscriberList = subscriberService.getAllSubscribers().getBody();
		return subscriberList.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping(path = "{subscriberId}")
	public SubscriberDto getSubscriberById(@PathVariable("subscriberId") Long subscriberId) {
		var subscriber = subscriberService.getSubscriberById(subscriberId).getBody();
		return convertToDto(subscriber);
	}

	@PostMapping
	public SubscriberDto createSubscriber(@Valid @RequestBody SubscriberDto newSubscriberDto) {
		var subscriber = convertToEntity(newSubscriberDto);
		var newSubscriber = subscriberService.createSubscriber(subscriber).getBody();
		return convertToDto(newSubscriber);
	}

	@PutMapping(path = "{subscriberId}")
	public SubscriberDto updateAdmin(@PathVariable("adminId") Long subscriberId,
			@Valid @RequestBody SubscriberWithoutPasswordDto subscriberDto) {
		var subscriber = convertToEntity(subscriberDto);
		var newSubscriber = subscriberService.updateSubscriber(subscriberId, subscriber).getBody();
		return convertToDto(newSubscriber);
	}

	@PutMapping(path = "{subscriberId}/password")
	public void updatePassword(@PathVariable("subscriberId") Long subscriberId,
			@Valid @RequestBody UserPasswordDto userPasswordDto) {
		subscriberService.updatePassword(subscriberId, userPasswordDto.getPassword());
	}

	@DeleteMapping(path = "{subscriberId}")
	public void deleteAdmin(@PathVariable("subscriberId") Long subscriberId) {
		subscriberService.deleteSubscriber(subscriberId);
	}

	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteAllAdmins() {
		return subscriberService.deleteAllSubscribers();
	}

}
