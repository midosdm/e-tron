package com.etron.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etron.models.Contrat;
import com.etron.services.ContratService;

@RestController
@RequestMapping(path = "/api/v1/contrats")

public class ContratController {

	@Autowired
	private ContratService contratService;

	@GetMapping
	public ResponseEntity<List<Contrat>> getAllContrats() {
		return contratService.getAllContrats();
	}

	@GetMapping("/annee/{dateFin}")
	public ResponseEntity<List<Contrat>> getByDateFin(@PathVariable("dateFin") LocalDate dateFin) {
		return contratService.getByDateFin(dateFin);
	}

	@GetMapping("/annee/{dateDebut}")
	public ResponseEntity<List<Contrat>> getByDateDebut(@PathVariable("dateFin") LocalDate dateDebut) {
		return contratService.getByDateDebut(dateDebut);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contrat> getContratById(@PathVariable("id") Long id) {
		return contratService.getContratById(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contrat> getContratByNumero(@PathVariable("id") int numeroContrat) {
		return contratService.getContratByNumero(numeroContrat);
	}

	@PostMapping("/{idSubscription}/{idSubscriber}/{numContrat}/{dateDebut}/{dateFin}/")
	public ResponseEntity<Contrat> createContrat(@PathVariable("idSubscription") Long idSubscription,
			@PathVariable("idSubscriber") Long idSubscriber, @PathVariable("numContrat") int numeroContrat,
			@PathVariable("dateDebut") LocalDate dateDebut, @PathVariable("dateFin") LocalDate dateFin) {
		return contratService.createContrat(idSubscription, idSubscriber, numeroContrat, dateDebut, dateFin);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteContrat(@PathVariable("id") Long id) {
		return contratService.deleteContrat(id);
	}

	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteAllContrats() {
		return contratService.deleteAllContrats();
	}

}
