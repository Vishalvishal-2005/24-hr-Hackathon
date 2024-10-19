package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class YourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String field1; // Corresponds to the first column in the CSV
    private String field2; // Corresponds to the second column in the CSV

    // Getters and setters
    /**
     * Retrieves the unique identifier associated with this object.
     *
     * @return the unique identifier (ID) as a {@code Long}.
     *
     * @throws IllegalStateException if the ID has not been initialized or is not available.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the identifier for this object.
     *
     * @param id the identifier to set, which should be a non-null Long value.
     * @throws IllegalArgumentException if the provided id is null.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the value of the field1 property.
     *
     * @return the value of field1 as a String.
     * @throws NullPointerException if field1 is null.
     */
    public String getField1() {
        return field1;
    }

    /**
     * Sets the value of the field1 property.
     *
     * @param field1 the new value for field1. This parameter cannot be null or empty.
     * @throws IllegalArgumentException if the provided field1 is null or empty.
     */
    public void setField1(String field1) {
        this.field1 = field1;
    }

    /**
     * Retrieves the value of the field2 property.
     *
     * @return the value of field2 as a String.
     *
     * @throws NullPointerException if field2 is null.
     */
    public String getField2() {
        return field2;
    }

    /**
     * Sets the value of the field2 property.
     *
     * @param field2 the new value for field2
     * @throws IllegalArgumentException if the provided field2 is null or empty
     */
    public void setField2(String field2) {
        this.field2 = field2;
    }
}
