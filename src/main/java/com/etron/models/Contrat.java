package com.etron.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contrat implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id = 0L;

	private int numeroContrat;

	private LocalDate dateDebut;

	private LocalDate dateFin;

	@OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL)
	private Set<Subscriber> subscriptionList = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "subscriber_id")
	private Subscriber subscriber;

	public Contrat(int numeroContrat, LocalDate dateDebut, LocalDate dateFin, Set<Subscriber> subscriptionList,
			Subscriber subscriber) {
		super();
		this.numeroContrat = numeroContrat;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.subscriptionList = subscriptionList;
		this.subscriber = subscriber;
	}

}
