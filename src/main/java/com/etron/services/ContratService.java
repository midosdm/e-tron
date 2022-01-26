package com.etron.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

	public ResponseEntity<List<Contrat>> getAllContrats() {

		try {
			List<Contrat> contrats = new ArrayList<Contrat>();

			contratRepository.findAll().forEach(contrats::add);

			if (contrats.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(contrats, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Contrat>> getContratByNumero(int numeroContrat) {
		try {
			List<Contrat> contrat = new ArrayList<Contrat>();

			if (numeroContrat == 0)
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);
			else
				contrat.add(contratRepository.findByNumeroContrat(numeroContrat));

			if (contrat.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(contrat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Contrat>> getByDateDebut(LocalDate dateDebut) {

		try {
			List<Contrat> contrats = new ArrayList<Contrat>();

			contratRepository.findByDateDebut(dateDebut).forEach(contrats::add);

			return new ResponseEntity<>(contrats, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Contrat>> getByDateFin(LocalDate dateFin) {

		try {
			List<Contrat> contrats = new ArrayList<Contrat>();

			contratRepository.findByDateDebut(dateFin).forEach(contrats::add);

			return new ResponseEntity<>(contrats, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Contrat> createContrat(Long idSubscription, Long idSubscriber, int numeroContrat,
			LocalDate dateDebut, LocalDate dateFin) {

		if (contratRepository.existsByNumeroContrat(numeroContrat)) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}

		Optional<Subscriber> subscriber = subscriberRepository.findById(idSubscriber);
		Optional<Subscription> subscription = subscriptionRepository.findById(idSubscription);

		if (subscriber.isPresent() && subscription.isPresent()) {
			Contrat _contrat = contratRepository
					.save(new Contrat(numeroContrat, dateDebut, dateFin, subscription.get(), subscriber.get()));
			return new ResponseEntity<>(_contrat, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Contrat> getContratById(Long id) {
		Optional<Contrat> contrat = contratRepository.findById(id);

		if (contrat.isPresent()) {
			return new ResponseEntity<>(contrat.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Contrat> updateContrat(long id, Contrat contrat) {
		Optional<Contrat> contratData = contratRepository.findById(id);

		if (contratData.isPresent()) {
			Contrat _contrat = contratData.get();

			_contrat.setDateDebut(contrat.getDateDebut());
			_contrat.setDateFin(contrat.getDateFin());
			_contrat.setNumeroContrat(contrat.getNumeroContrat());

			return new ResponseEntity<>(contratRepository.save(_contrat), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<HttpStatus> deleteContrat(Long id) {

		try {
			contratRepository.deleteById(id);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<HttpStatus> deleteAllContrats() {
		try {
			contratRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
