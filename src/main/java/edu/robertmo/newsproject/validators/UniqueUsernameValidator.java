package edu.robertmo.newsproject.validators;

import edu.robertmo.newsproject.entity.User;
import edu.robertmo.newsproject.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String > {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(username);

        //if username is not in use -> VALID
        return user.isEmpty();
    }
}
