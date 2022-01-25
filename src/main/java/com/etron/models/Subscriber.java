package com.etron.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscriber extends User implements Serializable {

    private String lastName;

    private String firstName;

    private LocalDateTime birthDate;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name="subscription_id")
    private Subscription subscription;
}
