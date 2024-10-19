package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean success = false;
    private String message;

    public User() {
    }

    public User(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Retrieves the unique identifier associated with this object.
     *
     * @return the unique identifier (ID) as a {@link Long} object.
     *
     * @throws IllegalStateException if the ID has not been initialized or is not available.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the identifier for this object.
     *
     * @param id the unique identifier to be set
     * @throws IllegalArgumentException if the provided id is null
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the username associated with this instance.
     *
     * @return the username as a {@code String}.
     * @throws NullPointerException if the username is null.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the current instance.
     *
     * @param username the username to be set. It must not be null or empty.
     * @throws IllegalArgumentException if the provided username is null or empty.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the email address associated with this object.
     *
     * @return the email address as a {@code String}.
     * @throws NullPointerException if the email address is not set (i.e., it is {@code null}).
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for the user.
     *
     * @param email the email address to be set
     * @throws IllegalArgumentException if the provided email is null or empty
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password associated with this instance.
     *
     * @return the password as a {@code String}.
     * @throws NullPointerException if the password is not set (i.e., it is {@code null}).
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user.
     *
     * @param password the new password to be set
     * @throws IllegalArgumentException if the password is null or empty
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if the operation was successful.
     *
     * @return {@code true} if the operation was successful, {@code false} otherwise.
     *
     * @throws IllegalStateException if the method is called before the operation is completed.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the success status of the operation.
     *
     * @param success a boolean value indicating the success status.
     *                If true, the operation is considered successful;
     *                if false, it is considered unsuccessful.
     *
     * @throws IllegalArgumentException if the success parameter is null.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Retrieves the message associated with this object.
     *
     * @return the message as a {@code String}.
     * @throws NullPointerException if the message is null.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message to the specified value.
     *
     * @param message the message to be set
     * @throws IllegalArgumentException if the message is null or empty
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
