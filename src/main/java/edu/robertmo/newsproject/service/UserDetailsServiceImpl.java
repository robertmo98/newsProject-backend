package edu.robertmo.newsproject.service;

import edu.robertmo.newsproject.dto.SignUpRequestDto;
import edu.robertmo.newsproject.dto.UserResponseDto;
import edu.robertmo.newsproject.repository.RoleRepository;
import edu.robertmo.newsproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import edu.robertmo.newsproject.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    //props:
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public UserResponseDto signUp(SignUpRequestDto dto) {
        //1) get the user role from the RoleRepository:
        val userRole = roleRepository.findByNameIgnoreCase("ROLE_USER")
                .orElseThrow( () -> new RuntimeException("please Contact admin"));//todo: create news exception and use it here!
        //2) if email/username exists -> Go Sign in (exception):
        val byUser = userRepository.findByUsernameIgnoreCase(dto.getUsername().trim());
        val byEmail = userRepository.findByEmailIgnoreCase(dto.getEmail().trim());

        if(byEmail.isPresent()) {
            throw new RuntimeException("email already exists");//todo: create class BadRequestException and use it here!
        } else if (byUser.isPresent()) {
            throw new RuntimeException("username already exists");//todo: create class BadRequestException and use it here!
        }

        //3) val user = new User(...encoded-password)
        val user = new User(
            null,
                dto.getUsername(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                List.of(),
                Set.of(userRole)
        );

        var savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //fetch our user entity from our database
        var user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        //map our roles to Springs SimpleGrantedAuthority:
        var roles = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();

        //return new org.springframework.security.core.userdetails.User
        //spring User implements UserDetails
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}