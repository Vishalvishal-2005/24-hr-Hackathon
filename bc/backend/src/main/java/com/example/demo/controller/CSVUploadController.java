package com.example.demo.controller;

import com.example.demo.service.CSVService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class CSVUploadController {

    private final CSVService csvService;

    public CSVUploadController(CSVService csvService) {
        this.csvService = csvService;
    }

    @PostMapping
    /**
     * Handles the file upload request and saves the uploaded file.
     *
     * @param file the MultipartFile object representing the uploaded file
     * @return a ResponseEntity containing a success message if the file is uploaded and stored successfully,
     *         or an error message if the upload fails
     * @throws IOException if an I/O error occurs while saving the file
     */
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            csvService.saveFile(file);
            return ResponseEntity.ok("File uploaded and stored successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }
}
