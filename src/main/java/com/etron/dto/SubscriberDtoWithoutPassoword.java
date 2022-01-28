package com.etron.dto;

import com.etron.models.Subscription;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberDtoWithoutPassoword {
    private Long id;

    @NotBlank(message = "First name cannot be null")
    private String lastName;

    @NotBlank(message = "Last name cannot be null")
    private String firstName;

    @NotBlank(message = "matricule cannot be null")
    private String matricule;

    @NotBlank(message = "email cannot be null")
    @Email(message = "Invalid email")
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private Subscription subscription;
}
