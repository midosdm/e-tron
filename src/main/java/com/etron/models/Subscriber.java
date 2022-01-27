package com.etron.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Subscriber extends AppUser implements Serializable {

	private String lastName;

	private String firstName;

	private LocalDate birthDate;

	@ManyToOne
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	private String matricule;

//	@OneToMany(mappedBy = "subscriber", cascade = CascadeType.ALL)
//	private Set<Vehicule> vehiculeList = new HashSet<>();

	public Subscriber(String lastName, String firstName, LocalDate birthDate, String matricule) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.matricule = matricule;
	}

}
