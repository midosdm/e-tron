package com.etron.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

	@Email(message = "Email should be valid")
	@JsonDeserialize(using = StringDeserializer.class)
	private String email;

	@NotBlank(message = "Password cannot be null")
	@Pattern(regexp = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,}\\z", message = "InvalidPassword")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;

}
