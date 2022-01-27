package com.etron.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.etron.exceptions.MessageResponse;
import com.etron.models.Role;
import com.etron.models.Subscriber;
import com.etron.models.enums.AppRole;
import com.etron.repositories.AppUserRepository;
import com.etron.repositories.RoleRepository;
import com.etron.repositories.SubscriberRepository;

@Service
public class SubscriberService {

	@Autowired
	SubscriberRepository subscriberRepository;
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	public SubscriberService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public ResponseEntity<?> getAllSubscribers() {

		try {
			List<Subscriber> subscribers = new ArrayList<Subscriber>();

			subscriberRepository.findAll().forEach(subscribers::add);

			if (subscribers.isEmpty()) {
				return new ResponseEntity<>(subscribers, HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(subscribers, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<Subscriber> getSubscriberById(Long id) {
		Optional<Subscriber> subscriber = subscriberRepository.findById(id);

		if (subscriber.isPresent()) {
			return new ResponseEntity<>(subscriber.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Subscriber> createSubscriber(Subscriber subscriber) {

		if (subscriberRepository.existsByEmail(subscriber.getEmail().toLowerCase())) {
//			return ResponseEntity.status(HttpStatus.NO_CONTENT)
//					.body(new MessageResponse("Warn: cet email existe déja"));
			new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		var role = roleRepository.findByAppRole(AppRole.SUBSCRIBER)
				.orElseGet(() -> roleRepository.save(Role.builder().appRole(AppRole.SUBSCRIBER).build()));

		subscriber.setRole(role);
		subscriber.setPassword(passwordEncoder.encode(subscriber.getPassword()));
		var s = subscriberRepository.save(subscriber);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	public ResponseEntity<?> updateSubscriber(Long id, Subscriber newSubscriber) {

		var oldSubscriber = getSubscriberById(id).getBody();

		if (appUserRepository.existsByEmail(newSubscriber.getEmail().toLowerCase())
				&& !newSubscriber.getEmail().equals(oldSubscriber.getEmail().toLowerCase())) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new MessageResponse("Warn: cet email existe déja"));
		}

		oldSubscriber.setEmail(newSubscriber.getEmail());
		oldSubscriber.setBirthDate(newSubscriber.getBirthDate());
		oldSubscriber.setFirstName(newSubscriber.getFirstName());
		oldSubscriber.setLastName(newSubscriber.getLastName());
		oldSubscriber.setMatricule(newSubscriber.getMatricule());
		var a = subscriberRepository.save(oldSubscriber);
		// var vToken = verificationTokenService.createVerificationToken(a);
		return new ResponseEntity<>(a, HttpStatus.OK);
	}

	public void updatePassword(Long id, String password) {
		var encPassword = passwordEncoder.encode(password);
		subscriberRepository.updatePassword(id, encPassword);
	}

	public ResponseEntity<?> deleteSubscriber(Long id) {

		try {
			subscriberRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> deleteAllSubscribers() {
		try {
			subscriberRepository.deleteAll();
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new MessageResponse("Warn: Aucun subscriber dans la BDD"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}
}
