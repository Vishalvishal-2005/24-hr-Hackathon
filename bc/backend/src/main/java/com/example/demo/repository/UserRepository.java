package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Retrieves a User object based on the provided email address.
     *
     * @param email the email address of the user to be retrieved
     * @return an Optional containing the User if found, or an empty Optional if no user is found with the given email
     * @throws IllegalArgumentException if the provided email is null or empty
     */
    Optional<User> findByEmail(String email);
    /**
     * Checks if a user exists in the system based on the provided email address.
     *
     * @param email the email address to check for existence
     * @return true if a user with the specified email exists, false otherwise
     * @throws IllegalArgumentException if the provided email is null or empty
     */
    boolean existsByEmail(String email);

}
