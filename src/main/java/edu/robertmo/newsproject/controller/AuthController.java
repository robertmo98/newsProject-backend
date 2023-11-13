package edu.robertmo.newsproject.controller;

import edu.robertmo.newsproject.dto.request.SignInRequestDto;
import edu.robertmo.newsproject.dto.request.UserProfilePicRequestDto;
import edu.robertmo.newsproject.dto.response.SignInResponseDto;
import edu.robertmo.newsproject.dto.request.SignUpRequestDto;
import edu.robertmo.newsproject.dto.response.UserResponseDto;
import edu.robertmo.newsproject.entity.Role;
import edu.robertmo.newsproject.repository.RoleRepository;
import edu.robertmo.newsproject.repository.UserRepository;
import edu.robertmo.newsproject.security.JWTProvider;
import edu.robertmo.newsproject.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

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
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto dto) {
        var user = authService.loadUserByUsername(dto.getUsername());
        var savedPassword = user.getPassword();
        var givenPassword = dto.getPassword();




        if(passwordEncoder.matches(givenPassword, savedPassword)) {
            boolean isAdmin = authService.isAdmin(dto.getUsername());
            String profilePic = authService.getProfilePic(dto.getUsername());


            //grant:
            var token = jwtProvider.generateToken(user.getUsername(), isAdmin, profilePic);

            return ResponseEntity.ok(new SignInResponseDto((token)));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/profile/update/picture")
    public ResponseEntity<UserResponseDto> updateProfilePic(
            @RequestBody UserProfilePicRequestDto dto,
            Authentication authentication) {
        return ResponseEntity.ok(authService.updateProfilePic(dto, authentication));
    }



}
