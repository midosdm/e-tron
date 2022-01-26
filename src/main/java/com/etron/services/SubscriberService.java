package com.etron.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.etron.models.Subscriber;
import com.etron.repositories.SubscriberRepository;

@Service
public class SubscriberService {

	@Autowired
	SubscriberRepository subscriberRepository;

	public ResponseEntity<List<Subscriber>> getAllSubscribers(String firstName) {

		try {
			List<Subscriber> subscribers = new ArrayList<Subscriber>();

			if (firstName == null)
				subscriberRepository.findAll().forEach(subscribers::add);
			else
				subscriberRepository.findByFirstNameContaining(firstName).forEach(subscribers::add);

			if (subscribers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(subscribers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
