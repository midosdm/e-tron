package com.etron.models;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String model;

    private String matricule;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;
}
