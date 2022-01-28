package com.etron.controllers;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etron.models.Contrat;
import com.etron.services.ContratService;

@RestController
@RequestMapping(path = "/api/v1/contrats")

public class ContratController {

	@Autowired
	private ContratService contratService;

	@GetMapping
	public ResponseEntity<?> getAllContrats() {
		return contratService.getAllContrats();
	}

	@GetMapping("/annee/{dateFin}")
	public ResponseEntity<?> getByDateFin(@PathVariable("dateFin") LocalDate dateFin) {
		return contratService.getByDateFin(dateFin);
	}

	@GetMapping("/annee/{dateDebut}")
	public ResponseEntity<?> getByDateDebut(@PathVariable("dateFin") LocalDate dateDebut) {
		return contratService.getByDateDebut(dateDebut);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getContratById(@PathVariable("id") Long id) {
		return contratService.getContratById(id);
	}

	@GetMapping("/{numeroContrat}")
	public ResponseEntity<?> getContratByNumero(@PathVariable("numeroContrat") int numeroContrat) {
		return contratService.getContratByNumero(numeroContrat);
	}

	@PostMapping("/createContrat")
	public ResponseEntity<?> createContrat(@RequestParam("idSubscription") Long idSubscription,
			@RequestParam("idSubscriber") Long idSubscriber, @Valid @RequestBody Contrat contrat) {
		return contratService.createContrat(idSubscription, idSubscriber, contrat);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteContrat(@PathVariable("id") Long id) {
		return contratService.deleteContrat(id);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteAllContrats() {
		return contratService.deleteAllContrats();
	}

}
