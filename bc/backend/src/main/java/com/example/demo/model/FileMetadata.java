package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "files")
public class FileMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String filePath;

    private LocalDateTime uploadTime;

    public FileMetadata() {
    }

    public FileMetadata(String filename, String filePath, LocalDateTime uploadTime) {
        this.filename = filename;
        this.filePath = filePath;
        this.uploadTime = uploadTime;
    }

    /**
     * Retrieves the unique identifier for this object.
     *
     * @return the unique identifier (ID) as a {@link Long} object.
     *
     * @throws NullPointerException if the ID has not been initialized and is null.
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
     * Retrieves the name of the file associated with this object.
     *
     * @return the filename as a {@code String}.
     * @throws IllegalStateException if the filename has not been set or is null.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the filename for the object.
     *
     * @param filename the name of the file to be set
     * @throws IllegalArgumentException if the filename is null or empty
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Retrieves the file path associated with this object.
     *
     * @return the file path as a {@code String}.
     * @throws IllegalStateException if the file path has not been initialized.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the file path for the object.
     *
     * @param filePath the path to the file to be set
     * @throws IllegalArgumentException if the provided filePath is null or empty
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves the upload time of the object.
     *
     * @return the upload time as a {@link LocalDateTime} object.
     * @throws IllegalStateException if the upload time has not been set.
     */
    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    /**
     * Sets the upload time for the current instance.
     *
     * @param uploadTime the LocalDateTime representing the upload time to be set
     * @throws IllegalArgumentException if the uploadTime is null
     */
    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }
}
