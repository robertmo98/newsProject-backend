package edu.robertmo.newsproject.controller;

import edu.robertmo.newsproject.dto.request.SignInRequestDto;
import edu.robertmo.newsproject.dto.response.SignInResponseDto;
import edu.robertmo.newsproject.dto.request.SignUpRequestDto;
import edu.robertmo.newsproject.dto.response.UserResponseDto;
import edu.robertmo.newsproject.security.JWTProvider;
import edu.robertmo.newsproject.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserDetailsServiceImpl authService;

    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody @Valid SignUpRequestDto dto) {


        return new ResponseEntity<>(authService.signUp(dto), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody @Valid SignInRequestDto dto) {
        var user = authService.loadUserByUsername(dto.getUsername());

        var savedPassword = user.getPassword();
        var givenPassword = dto.getPassword();

        if(passwordEncoder.matches(givenPassword, savedPassword)) {
            //grant:
            var token = jwtProvider.generateToken(user.getUsername());

            return ResponseEntity.ok(new SignInResponseDto((token)));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
