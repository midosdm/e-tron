package com.etron.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscriber extends AppUser implements Serializable {

	private String lastName;

	private String firstName;

	private LocalDateTime birthDate;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	@OneToMany(mappedBy = "subscriber", cascade = CascadeType.ALL)
	private Set<Vehicule> vehiculeList = new HashSet<>();

	public Subscriber(String lastName, String firstName, LocalDateTime birthDate, Subscription subscription) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.subscription = subscription;
	}

}
