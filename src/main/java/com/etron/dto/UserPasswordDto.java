package com.etron.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordDto {
    @NotBlank(message = "Password cannot be null")
    @Pattern(regexp = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,}\\z"
            , message = "InvalidPassword")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
