package com.techflow.techflow.dto.request;

import com.techflow.techflow.validator.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    @Email(message = "L'email est obligatoire")
    @NotBlank(message = "L'email doit être valide")
    private String email;
    @StrongPassword
    private String password;
    private String fullName;

}