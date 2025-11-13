
package com.campusconnect.controller;

import com.campusconnect.model.FoundItem;
import com.campusconnect.service.FileUploadService;
import com.campusconnect.service.LostFoundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/found")
@CrossOrigin(origins = "*")
@Tag(name = "Found Items", description = "Enhanced Found Items Management API with Image Support and Claim Tracking")
public class FoundItemController {

    @Autowired
    private LostFoundService service;
    
    @Autowired
    private FileUploadService fileUploadService;

    // Get all found items
    @GetMapping
    @Operation(summary = "Get all found items", description = "Retrieve all reported found items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved found items"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<FoundItem>> getAllFoundItems() {
        List<FoundItem> items = service.getAllFoundItems();
        return ResponseEntity.ok(items);
    }

    // Report new found item
    @PostMapping
    @Operation(summary = "Report found item", description = "Report a new found item to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found item reported successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<FoundItem> addFoundItem(
            @Parameter(description = "Found item details") @RequestBody FoundItem foundItem) {
        try {
            FoundItem savedItem = service.addFoundItem(foundItem);
            return ResponseEntity.ok(savedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
