package com.campusconnect.controller;

import com.campusconnect.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
@Tag(name = "File Management", description = "File upload and download operations")
public class FileController {

    @Autowired
    private FileUploadService fileUploadService;

    @Value("${file.upload.dir:uploads}")
    private String uploadDir;

    @PostMapping("/upload")
    @Operation(summary = "Upload file", description = "Upload an image file to the server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File uploaded successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid file or file size exceeds limit"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Map<String, String>> uploadFile(
            @Parameter(description = "Image file to upload") @RequestParam("file") MultipartFile file,
            @Parameter(description = "Category for organizing files") @RequestParam(value = "category", defaultValue = "general") String category) {
        
        try {
            String filePath = fileUploadService.uploadImage(file, category);
            String fileUrl = fileUploadService.getFileUrl(filePath);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "File uploaded successfully");
            response.put("filePath", filePath);
            response.put("fileUrl", fileUrl);
            response.put("originalName", file.getOriginalFilename());
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (IOException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to upload file: " + e.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    @GetMapping("/{category}/{filename}")
    @Operation(summary = "Download file", description = "Download/serve an uploaded file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File served successfully"),
            @ApiResponse(responseCode = "404", description = "File not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Resource> downloadFile(
            @Parameter(description = "File category") @PathVariable String category,
            @Parameter(description = "Filename to download") @PathVariable String filename) {
        
        try {
            Path filePath = Paths.get(uploadDir, category, filename);
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{category}/{filename}")
    @Operation(summary = "Delete file", description = "Delete an uploaded file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File deleted successfully"),
            @ApiResponse(responseCode = "404", description = "File not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Map<String, String>> deleteFile(
            @Parameter(description = "File category") @PathVariable String category,
            @Parameter(description = "Filename to delete") @PathVariable String filename) {
        
        try {
            String filePath = category + "/" + filename;
            fileUploadService.deleteImage(filePath);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "File deleted successfully");
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to delete file: " + e.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
}