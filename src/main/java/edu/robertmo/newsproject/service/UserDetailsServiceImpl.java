package edu.robertmo.newsproject.service;

import edu.robertmo.newsproject.dto.request.SignUpRequestDto;
import edu.robertmo.newsproject.dto.request.UserProfilePicRequestDto;
import edu.robertmo.newsproject.dto.response.UserResponseDto;
import edu.robertmo.newsproject.entity.Role;
import edu.robertmo.newsproject.error.BadRequestException;
import edu.robertmo.newsproject.repository.RoleRepository;
import edu.robertmo.newsproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import edu.robertmo.newsproject.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService, edu.robertmo.newsproject.service.UserDetailsService {
    //props:
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public UserResponseDto signUp(SignUpRequestDto dto) {
        /**
         * 1) get the user role from the RoleRepository:
         */
        val userRole = roleRepository.findByNameIgnoreCase("ROLE_USER")
                .orElseThrow( () -> new RuntimeException("please Contact admin"));
        /**
         * 2) if email/username exists -> Go Sign in (exception):
         */
        val byUser = userRepository.findByUsernameIgnoreCase(dto.getUsername().trim());
        val byEmail = userRepository.findByEmailIgnoreCase(dto.getEmail().trim());

        if(byEmail.isPresent()) {
            throw new BadRequestException("Email","already exists");
        } else if (byUser.isPresent()) {
            throw new RuntimeException("username already exists");//todo: create class BadRequestException and use it here!
        }

        /**
         * 3) val user = new User(...encoded-password)
         */
        val user = new User(
            null,
                dto.getUsername(),
                dto.getEmail(),
                null,
                passwordEncoder.encode(dto.getPassword()),
                List.of(),
                Set.of(userRole),
                List.of()
                );

        var savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * fetch our user entity from the database
         */
        User user = getUser(username);

        /**
         * map our roles to Springs SimpleGrantedAuthority:
         */
        var roles = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();

        /**
         * return new org.springframework.security.core.userdetails.User
         * spring User implements UserDetails
         */
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }


    public boolean isAdmin(String username) {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(username);

        Optional <Role> roleAdmin = roleRepository.findByNameIgnoreCase("ROLE_ADMIN");

        if (user != null) {
            Set<Role> roles = user.get().getRoles();

            for(Role role : roles) {
                if("ROLE_ADMIN".equals(role.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getProfilePic(String username) {
        User user = getUser(username);
        return user.getProfilePic();
    }


    @Override
    @Transactional
    public UserResponseDto updateProfilePic(UserProfilePicRequestDto dto, Authentication authentication) {
        var username = authentication.getName();
        User user = getUser(username);

        user.setProfilePic(dto.getProfilePic());

        var userAfterUpdate = getUser(username);

        return modelMapper.map(userAfterUpdate, UserResponseDto.class);
    }

    @Transactional
    public UserResponseDto deleteUser(Authentication authentication) {
        var username = authentication.getName();
        User user = getUser(username);

        userRepository.delete(user);

        return modelMapper.map(user, UserResponseDto.class);
    }

    private User getUser(String username) {
        var user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return user;
    }

}
