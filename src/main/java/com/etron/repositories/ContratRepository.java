package com.etron.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etron.models.Contrat;

public interface ContratRepository extends JpaRepository<Contrat, Long> {

	Contrat findByNumeroContrat(int numeroContrat);

	List<Contrat> findByDateDebut(LocalDate dateDebut);

	List<Contrat> findByDateFin(LocalDate dateFin);

	Boolean existsByNumeroContrat(int numeroContrat);
}
