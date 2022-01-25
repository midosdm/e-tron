package com.etron.models;

import com.etron.models.enums.AppRole;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AppRole appRole;

}
