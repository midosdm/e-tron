package com.etron.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.etron.exceptions.MessageResponse;
import com.etron.models.Contrat;
import com.etron.models.Subscriber;
import com.etron.models.Subscription;
import com.etron.repositories.ContratRepository;
import com.etron.repositories.SubscriberRepository;
import com.etron.repositories.SubscriptionRepository;

@Service
public class ContratService {

	@Autowired
	ContratRepository contratRepository;
	@Autowired
	SubscriberRepository subscriberRepository;
	@Autowired
	SubscriptionRepository subscriptionRepository;
	@Autowired
	SubscriptionService subscriptionService;
	@Autowired
	SubscriberService subscriberService;

	public ResponseEntity<?> getAllContrats() {

		try {
			List<Contrat> contrats = new ArrayList<Contrat>();

			contratRepository.findAll().forEach(contrats::add);

			if (contrats.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body(new MessageResponse("Warn: Aucun contrat dans la BDD"));
			}

			return new ResponseEntity<>(contrats, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> getContratByNumero(int numeroContrat) {
		try {
			Contrat contrat = new Contrat();

			if (numeroContrat == 0)
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(new MessageResponse("Error: Numero contrat cannot be null"));
			else
				contrat = contratRepository.findByNumeroContrat(numeroContrat);

			if (contrat == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse(
						"Warn: Aucun contrat avec le numero " + numeroContrat + "exite dans la BDD"));
			}

			return new ResponseEntity<>(contrat, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> getByDateDebut(LocalDate dateDebut) {

		try {
			List<Contrat> contrats = new ArrayList<Contrat>();

			contratRepository.findByDateDebut(dateDebut).forEach(contrats::add);

			return new ResponseEntity<>(contrats, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> getByDateFin(LocalDate dateFin) {

		try {
			List<Contrat> contrats = new ArrayList<Contrat>();

			contratRepository.findByDateDebut(dateFin).forEach(contrats::add);

			return new ResponseEntity<>(contrats, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> createContrat(Long idSubscription, Long idSubscriber, Contrat contrat) {

		if (contratRepository.existsByNumeroContrat(contrat.getNumeroContrat())) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new MessageResponse("Error: Numero contrat exists already"));
		}

		Optional<Subscriber> subscriber = subscriberRepository.findById(idSubscriber);
		Optional<Subscription> subscription = subscriptionRepository.findById(idSubscription);

		if (subscriber.isPresent() && subscription.isPresent()) {

			subscriptionService.addSubscriber(idSubscriber, idSubscription);
			subscriberService.addSubscription(idSubscriber, idSubscription);

			Contrat _contrat = contratRepository.save(new Contrat(contrat.getNumeroContrat(), subscription.get(),
					subscriber.get(), contrat.getDateDebut(), contrat.getDateFin()));
			return new ResponseEntity<>(_contrat, HttpStatus.CREATED);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> getContratById(Long id) {
		Optional<Contrat> contrat = contratRepository.findById(id);

		if (contrat.isPresent()) {
			return new ResponseEntity<>(contrat.get(), HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: Contrat not found"));
		}
	}

	public ResponseEntity<?> deleteContrat(Long id) {

		try {
			contratRepository.deleteById(id);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

	public ResponseEntity<?> deleteAllContrats() {
		try {
			contratRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error: INTERNAL SERVER ERROR"));
		}
	}

}
