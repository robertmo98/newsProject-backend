package edu.robertmo.newsproject.controller;

import edu.robertmo.newsproject.dto.request.SignInRequestDto;
import edu.robertmo.newsproject.dto.request.UserProfilePicRequestDto;
import edu.robertmo.newsproject.dto.response.SignInResponseDto;
import edu.robertmo.newsproject.dto.request.SignUpRequestDto;
import edu.robertmo.newsproject.dto.response.UserResponseDto;
import edu.robertmo.newsproject.security.JWTProvider;
import edu.robertmo.newsproject.service.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@Tag(name = "Authentication controller", description = "Authentication and user related requests")
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserDetailsServiceImpl authService;


    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "Signup with username, email and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Signed up successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SignInResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "ROLE_USER is missing in the database",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Email or username is already in use",
                    content = @Content)
    })
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody @Valid SignUpRequestDto dto) {
        return new ResponseEntity<>(authService.signUp(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Sign in - regular user or admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Signed in successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SignInResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content)
    })
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


    @Operation(summary = "Update profile picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated picture successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content)
    })
    @PutMapping("/profile/update/picture")
    public ResponseEntity<UserResponseDto> updateProfilePic(
            @RequestBody UserProfilePicRequestDto dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(authService.updateProfilePic(dto, authentication));
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user has been deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content)
    })
    @DeleteMapping("/profile/delete")
    public ResponseEntity<UserResponseDto> deleteUser(
        Authentication authentication
    ) {
        return ResponseEntity.ok(authService.deleteUser(authentication));
    }

}
