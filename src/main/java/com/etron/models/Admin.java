package com.etron.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin extends AppUser implements Serializable {
    private String lastName;

    private String firstName;

    private LocalDate birthDate;
}
