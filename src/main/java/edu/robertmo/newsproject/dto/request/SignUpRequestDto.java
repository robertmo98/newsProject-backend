package edu.robertmo.newsproject.dto.request;

import edu.robertmo.newsproject.validators.UniqueEmail;
import edu.robertmo.newsproject.validators.UniqueUsername;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    @NotNull
    @NotEmpty
    @UniqueUsername
    @Size(min = 2, max = 20, message = "username must contain at least 2 letters")
    private String username;

    @NotNull
    @NotEmpty
    @UniqueEmail
    @Email
    private String email;

    @NotNull(message = "password is mandatory")
    @NotEmpty
    @Size(min = 8, max = 20)
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?\\W).{8,20}$",
            message = "password must contain at least 8 characters, one or more lower case letters, uppercase letter, symbol, digits"
    )
    private String password;
}
