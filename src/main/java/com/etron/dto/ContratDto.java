package com.etron.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.etron.models.Subscriber;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContratDto implements Serializable {
	private Long id = 0L;

	@NotBlank(message = "Number contrat cnnot be null")
	private int numeroContrat;

//	@JsonFormat(pattern = "dd-MM-yyyy")
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
//	private LocalDate dateDebut;
//
//	@JsonFormat(pattern = "dd-MM-yyyy")
//	@DateTimeFormat(pattern = "dd-MM-yyyy")
//	private LocalDate dateFin;

	private Set<Subscriber> subscriptionList = new HashSet<>();

	private Subscriber subscriber;
}
