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
import com.etron.repositories.ContratRepository;

@Service
public class ContratService {

	@Autowired
	ContratRepository contratRepository;

	public ResponseEntity<List<Contrat>> getAllContrats(int numeroContrat) {

		try {
			List<Contrat> contrats = new ArrayList<Contrat>();

			if (numeroContrat == 0)
				contratRepository.findAll().forEach(contrats::add);
			else
				contrats.add(contratRepository.findByNumeroContrat(numeroContrat));

			if (contrats.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(contrats, HttpStatus.OK);
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

	public ResponseEntity<Contrat> createContrat(Contrat contrat) {
		try {
			if (contratRepository.existsByNumeroContrat(contrat.getNumeroContrat())) {
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);
			}

			Contrat _contrat = contratRepository.save(new Contrat(contrat.getNumeroContrat(), contrat.getDateDebut(),
					contrat.getDateFin(), contrat.getSubscriptionList(), contrat.getSubscriber()));

			return new ResponseEntity<>(_contrat, HttpStatus.CREATED);
		} catch (Exception e) {
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
