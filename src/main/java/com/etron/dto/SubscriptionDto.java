package com.etron.dto;

import com.etron.models.Subscriber;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto implements Serializable {

    private Long id = 0L;

    @NotNull(message = "subcription type cannot be null")
    private String subscriptionType;

    @NotNull(message = "Price cannot be null")
    private Double fraisDeBase;

    @NotNull(message = "Price cannot be null")
    private Double chargeAc;

    @NotNull(message = "Price cannot be null")
    private Double chargeDc;

    @NotNull(message = "Price cannot be null")
    private boolean isChargeRapideActive;

    @NotNull(message = "Price cannot be null")
    private double chargeRapide;

    @NotBlank(message = "Duration cannot be null")
    private String duration;

    private Set<Subscriber> subscriberList = new HashSet<>();
}
