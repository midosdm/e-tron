package com.etron.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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
public class Subscriber extends User implements Serializable {

	private String lastName;

	private String firstName;

	private LocalDateTime birthDate;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	public Subscriber(String lastName, String firstName, LocalDateTime birthDate, Subscription subscription) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.subscription = subscription;
	}

}
