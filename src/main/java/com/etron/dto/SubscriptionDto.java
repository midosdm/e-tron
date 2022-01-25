package com.etron.dto;

import com.etron.models.Subscriber;
import com.etron.models.enums.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {

    private Long id = 0L;

    private SubscriptionType subscriptionType;

    @NotBlank(message = "Duration cannot be null")
    private String duration;

    private Set<Subscriber> subscriberList = new HashSet<>();
}
