package com.etron.models;

import com.etron.models.enums.SubscriptionType;
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
public class Subscription implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id = 0L;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    private String duration;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
    private Set<Subscriber> subscriberList = new HashSet<>();
}
