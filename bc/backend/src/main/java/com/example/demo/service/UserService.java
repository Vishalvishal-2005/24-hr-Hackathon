package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Retrieves a list of all users from the user repository.
     *
     * @return a List of User objects representing all users in the repository.
     * @throws DataAccessException if there is an error accessing the user data.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user to be retrieved
     * @return an Optional containing the User if found, or an empty Optional if no user is found with the given id
     * @throws IllegalArgumentException if the provided id is null
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Saves a user to the repository after encoding their password.
     *
     * This method takes a {@link User} object, encodes its password using a password encoder,
     * and then saves the user to the underlying data store. The encoded password replaces the
     * original password in the user object before saving.
     *
     * @param user the {@link User} object to be saved, which must not be null
     * @return the saved {@link User} object, with the encoded password
     * @throws IllegalArgumentException if the provided user is null or if any required fields
     *         in the user object are missing or invalid
     * @throws DataAccessException if there is an error accessing the data store while saving the user
     */
    public User saveUser(User user) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
    /**
     * Updates an existing user in the repository.
     *
     * This method checks if a user with the specified ID exists. If the user exists,
     * it updates the user's information. If a new password is provided, it will be
     * re-encoded before saving. The user's ID is set to ensure the correct user is updated.
     *
     * @param id   the ID of the user to be updated
     * @param user the User object containing the updated information
     * @return the updated User object, or null if no user with the specified ID exists
     *
     * @throws IllegalArgumentException if the provided user object is null
     * @throws SomeSpecificException if there is an error during the update process
     */
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id); // Make sure the ID is set for update
            // Optional: re-encode password if provided
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return userRepository.save(user);
        }
        return null;
    }
    /**
     * Validates a user's credentials by checking the provided email and password.
     *
     * This method retrieves a user from the repository based on the provided email.
     * If the user exists, it checks if the provided password matches the stored password.
     * If the credentials are valid, the user object is updated to indicate a successful sign-in
     * and returned. If the credentials are invalid, a new user object is returned with a failure message.
     *
     * @param email    the email of the user to validate
     * @param password the password of the user to validate
     * @return a User object containing the success status and message
     *
     * @throws IllegalArgumentException if the email or password is null
     */
    public User validateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                user.setSuccess(true);
                user.setMessage("Sign-in successful!");
                return user;
            }
        }
        User response = new User();
        response.setSuccess(false);
        response.setMessage("Invalid email or password.");
        return response;
    }
    /**
     * Checks if the specified email is already registered in the system.
     *
     * @param email the email address to check for registration
     * @return true if the email is already registered, false otherwise
     * @throws IllegalArgumentException if the provided email is null or empty
     */
    public boolean isEmailAlreadyRegistered(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Deletes a user from the repository based on the provided user ID.
     *
     * @param id the ID of the user to be deleted
     * @throws IllegalArgumentException if the provided ID is null
     * @throws UserNotFoundException if no user exists with the provided ID
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
