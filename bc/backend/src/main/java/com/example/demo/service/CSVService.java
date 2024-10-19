package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileMetadata;
import com.example.demo.repository.FileMetadataRepository;

@Service
public class CSVService {

    private final FileMetadataRepository fileMetadataRepository;

    public CSVService(FileMetadataRepository fileMetadataRepository) {
        this.fileMetadataRepository = fileMetadataRepository;
    }

    /**
     * Saves the uploaded file to the user's home directory under the "uploads" folder.
     *
     * <p>This method checks if the provided file is not empty, creates the necessary
     * directories if they do not exist, and transfers the file to the specified location.
     * It also saves metadata about the file to the database.</p>
     *
     * @param file the MultipartFile object representing the uploaded file
     * @throws IOException if an I/O error occurs during file saving or if the file is empty
     *                     This exception is thrown specifically when the provided file is empty.
     */
    public void saveFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // Set directory path to the user's home directory
            String directoryPath = System.getProperty("user.home") + "/uploads/";
            
            // Construct the full path to save the file
            String filePath = directoryPath + file.getOriginalFilename();
            
            // Ensure the directory exists; if not, create it
            Path directory = Paths.get(directoryPath);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);  // Create directory if it doesn't exist
            }

            // Transfer the file to the target location
            File destFile = new File(filePath);
            file.transferTo(destFile);

            // Save metadata to the database
            FileMetadata fileMetadata = new FileMetadata(file.getOriginalFilename(), filePath, LocalDateTime.now());
            fileMetadataRepository.save(fileMetadata);
        } else {
            throw new IOException("File is empty.");
        }
    }
}
