package com.etron.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubscriptionDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id = 0L;

    private Double fraisDeBase;

    private Double chargeAc;

    private Double chargeDc;

    private boolean isChargeRapideActive;

    private double chargeRapide;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Role subscription;
}
