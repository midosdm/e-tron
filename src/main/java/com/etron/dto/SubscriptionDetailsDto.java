package com.etron.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class SubscriptionDetailsDto {

    private Long id = 0L;

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
}
