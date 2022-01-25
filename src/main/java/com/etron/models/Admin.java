package com.etron.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin extends User implements Serializable {
    private String lastName;

    private String firstName;

    private LocalDateTime birthDate;
}
