package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;

import jakarta.persistence.EntityExistsException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    /**
     * Retrieves a list of all users from the user service.
     *
     * @return a List of User objects representing all users.
     * @throws UserServiceException if there is an error retrieving the users from the user service.
     */
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user to be retrieved
     * @return a {@link ResponseEntity} containing the user if found, or a 404 Not Found response if the user does not exist
     *
     * @throws IllegalArgumentException if the provided id is null
     */
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    /**
     * Creates a new user and saves it to the database.
     *
     * @param user the User object containing the details of the user to be created
     * @return a ResponseEntity containing the saved User object if successful,
     *         or an error message if the user could not be created
     *
     * @throws EntityExistsException if a user with the same email already exists
     * @throws Exception for any other unexpected errors that may occur during user creation
     */
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new User(false, "Email already exists."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new User(false, "An error occurred."));
        }
    }
    @PutMapping("/{id}")
    /**
     * Updates an existing user identified by the given ID.
     *
     * This method takes a user ID and a User object as input, updates the user information
     * in the system, and returns the updated User object wrapped in a ResponseEntity.
     * If the user with the specified ID does not exist, it returns a 404 Not Found response.
     *
     * @param id the ID of the user to be updated
     * @param user the User object containing the updated information
     * @return a ResponseEntity containing the updated User object if successful,
     *         or a 404 Not Found response if the user does not exist
     * @throws IllegalArgumentException if the provided user ID is null or invalid
     * @throws UserNotFoundException if no user is found with the specified ID
     * @throws Exception for any other unexpected errors during the update process
     */
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    /**
     * Deletes a user identified by the given ID.
     *
     * This method handles the deletion of a user from the system.
     * It invokes the user service to perform the deletion operation.
     * If the deletion is successful, it returns a response with no content
     * and a status of 204 No Content.
     *
     * @param id the ID of the user to be deleted
     * @return a ResponseEntity with no content if the deletion is successful
     * @throws UserNotFoundException if no user with the specified ID exists
     * @throws UnauthorizedAccessException if the user does not have permission to delete the user
     */
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/signin")
    /**
     * Signs in a user by validating their credentials and generating a JWT token.
     *
     * @param user the User object containing the email and password for authentication
     * @return a ResponseEntity containing the JWT token if authentication is successful,
     *         or an unauthorized status with an error message if authentication fails
     * @throws IllegalArgumentException if the provided user object is null or contains invalid data
     * @throws AuthenticationException if there is an error during the authentication process
     */
    public ResponseEntity<?> signIn(@RequestBody User user) {
        User response = userService.validateUser(user.getEmail(), user.getPassword());
        if (response.isSuccess()) {
            // Generate JWT token
            String token = jwtUtil.generateToken(response.getEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.getMessage());
        }
    }
}
