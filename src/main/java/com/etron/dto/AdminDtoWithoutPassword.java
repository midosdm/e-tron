package com.etron.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminDtoWithoutPassword {
    private Long id;

    @NotBlank(message = "First name cannot be null")
    private String lastName;

    @NotBlank(message = "Last name cannot be null")
    private String firstName;

    @Email(message = "Email should be valid")
    @JsonDeserialize(using = StringDeserializer.class)
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime birthDate;
}
