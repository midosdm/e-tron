package com.etron.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    private LocalDate dateDebut;

    private LocalDate dateFin;


    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL)
    private Set<Subscriber> subscriptionList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;
}
