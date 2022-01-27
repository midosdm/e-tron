package com.etron.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.etron.exceptions.MessageResponse;
import com.etron.exceptions.TypeIsTakenException;
import com.etron.models.Subscription;
import com.etron.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
	@Autowired
	SubscriptionRepository subscriptionRepository;

	public ResponseEntity<?> getAllSubscriptions() {
		try {
			List<Subscription> subscriptions = new ArrayList<Subscription>();
			subscriptionRepository.findAll().forEach(subscriptions::add);
			if (subscriptions.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body(new MessageResponse("Warn: Aucune abonnement dans la BDD"));
			} else {
				return new ResponseEntity<>(subscriptions, HttpStatus.OK);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> getByType(String type) {
		try {
			Subscription subscription = new Subscription();
			subscription = subscriptionRepository.getBySubscriptionType(type);
			if (subscription == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body(new MessageResponse("Warn: Aucune abonnement dans la BDD avec le type " + type));
			} else {
				return new ResponseEntity<>(subscription, HttpStatus.OK);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> getById(Long id) {
		try {
			Subscription subscription = new Subscription();
			subscription = subscriptionRepository.getById(id);
			if (subscription == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body(new MessageResponse("Warn: Aucune abonnement dans la BDD avec l'id " + id));
			} else {
				return new ResponseEntity<>(subscription, HttpStatus.OK);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> createSubscription(Subscription subscription) {
		try {
			if (subscriptionRepository.existsBySubscriptionType(subscription.getSubscriptionType().toString())) {
				throw new TypeIsTakenException(Subscription.class, subscription.getSubscriptionType().toString());
			}

			Subscription newSubscription = subscriptionRepository
					.save(new Subscription(subscription.getSubscriptionType(), subscription.getDuration(),
							subscription.getFraisDeBase(), subscription.getChargeAc(), subscription.getChargeDc(),
							subscription.isChargeRapideActive(), subscription.getChargeRapide()));
			return new ResponseEntity<>(newSubscription, HttpStatus.CREATED);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> updateSubscription(Long id, Subscription subscription) {
		Optional<Subscription> subscriptionData = subscriptionRepository.findById(id);

		if (subscriptionData.isPresent()) {
			Subscription newSubscription = subscriptionData.get();

			newSubscription.setSubscriptionType(subscription.getSubscriptionType());
			newSubscription.setSubscriberList(subscription.getSubscriberList());
			newSubscription.setDuration(subscription.getDuration());
			newSubscription.setFraisDeBase(subscription.getFraisDeBase());
			newSubscription.setChargeAc(subscription.getChargeAc());
			newSubscription.setChargeDc(subscription.getChargeDc());
			newSubscription.setChargeRapide(subscription.getChargeRapide());
			// newSubscription.isChargeRapideActive(subscription.isChargeRapideActive);
			return new ResponseEntity<>(subscriptionRepository.save(newSubscription), HttpStatus.OK);

		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> deleteSubscription(Long id) {
		try {
			subscriptionRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> deleteAllSubscriptions() {
		try {
			subscriptionRepository.deleteAll();
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new MessageResponse("Warn: Aucune abonnement dans la BDD"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}
}
