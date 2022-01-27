package com.etron.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = 0L;

	@NotBlank(message = "Number contrat cnnot be null")
	private int numeroContrat;

	@NotBlank(message = "date debut cannot be null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDate dateDebut;

	@NotBlank(message = "date fin cannot be null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDate dateFin;

	@ManyToOne
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	@ManyToOne
	@JoinColumn(name = "subscriber_id")
	private Subscriber subscriber;

	public Contrat(int numeroContrat, LocalDate dateDebut, LocalDate dateFin, Subscription subscription,
			Subscriber subscriber) {
		super();
		this.numeroContrat = numeroContrat;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.subscription = subscription;
		this.subscriber = subscriber;
	}

}
