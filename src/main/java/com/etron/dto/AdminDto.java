package com.etron.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AdminDto implements Serializable {

    private Long id;

    @NotBlank(message = "First name cannot be null")
    private String lastName;

    @NotBlank(message = "Last name cannot be null")
    private String firstName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime birthDate;
}
