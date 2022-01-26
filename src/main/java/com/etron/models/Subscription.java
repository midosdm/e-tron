package com.etron.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id = 0L;

    private String subscriptionType;

    private String duration;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
    private Set<Subscriber> subscriberList = new HashSet<>();

    private Double fraisDeBase;

    private Double chargeAc;

    private Double chargeDc;

    private boolean isChargeRapideActive;

    private double chargeRapide;

    public Subscription(String subscriptionType, String duration,Double fraisDeBase, Double chargeAc, Double chargeDc, boolean isChargeRapideActive, Double chargeRapide) {
        this.subscriptionType = subscriptionType;
        this.duration = duration;
        this.subscriberList = subscriberList;
    }
}
