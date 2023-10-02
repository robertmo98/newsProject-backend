package edu.robertmo.newsproject.controller;

import edu.robertmo.newsproject.dto.SignInRequestDto;
import edu.robertmo.newsproject.dto.SignUpRequestDto;
import edu.robertmo.newsproject.dto.UserResponseDto;
import edu.robertmo.newsproject.security.JWTProvider;
import edu.robertmo.newsproject.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserDetailsServiceImpl authService;

    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignUpRequestDto dto) {


        return new ResponseEntity<>(authService.signUp(dto), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequestDto dto) {
        var user = authService.loadUserByUsername(dto.getUsername());

        var savedPassword = user.getPassword();
        var givenPassword = dto.getPassword();

        if(passwordEncoder.matches(givenPassword, savedPassword)) {
            //grant:
            var token = jwtProvider.generateToken(user.getUsername());

            return ResponseEntity.ok(Map.of("jwt", token));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
