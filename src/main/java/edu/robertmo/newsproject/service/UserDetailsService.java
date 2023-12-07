package edu.robertmo.newsproject.service;

import edu.robertmo.newsproject.dto.request.UserProfilePicRequestDto;
import edu.robertmo.newsproject.dto.response.UserResponseDto;
import org.springframework.security.core.Authentication;

public interface UserDetailsService {
    UserResponseDto updateProfilePic(UserProfilePicRequestDto dto, Authentication authentication);

    UserResponseDto deleteUser(Authentication authentication);
}
