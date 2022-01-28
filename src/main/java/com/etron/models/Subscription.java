package com.etron.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

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
public class Subscription implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = 0L;

	@NotNull(message = "subcription type cannot be null")
	private String subscriptionType;

	private String duration;

	@OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
	private Set<Subscriber> subscriberList = new HashSet<>();

	@NotNull(message = "Price cannot be null")
	private Double fraisDeBase;

	@NotNull(message = "Price cannot be null")
	private Double chargeAc;

	@NotNull(message = "Price cannot be null")
	private Double chargeDc;

	private boolean isChargeRapideActive;

	@NotNull(message = "Price cannot be null")
	private double chargeRapide;

	public Subscription(String subscriptionType, String duration, Double fraisDeBase, Double chargeAc, Double chargeDc,
			boolean isChargeRapideActive, Double chargeRapide) {
		this.subscriptionType = subscriptionType;
		this.duration = duration;
		this.fraisDeBase = fraisDeBase;
		this.chargeAc = chargeAc;
		this.chargeDc = chargeDc;
		this.isChargeRapideActive = isChargeRapideActive;
		this.chargeRapide = chargeRapide;
	}

	public Subscription(Subscriber subscriberList) {
		super();
		this.subscriberList = (Set<Subscriber>) subscriberList;
	}

}
