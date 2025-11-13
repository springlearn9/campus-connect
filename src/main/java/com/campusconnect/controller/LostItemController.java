
package com.campusconnect.controller;

import com.campusconnect.model.LostItem;
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
@RequestMapping("/api/lost")
@CrossOrigin(origins = "*")
@Tag(name = "Lost Items", description = "Enhanced Lost Items Management API with image support and advanced filtering")
public class LostItemController {

    @Autowired
    private LostFoundService service;
    
    @Autowired
    private FileUploadService fileUploadService;

    // Get all lost items
    @GetMapping
    @Operation(summary = "Get all lost items", description = "Retrieve all reported lost items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved lost items"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<LostItem>> getAllLostItems() {
        List<LostItem> items = service.getAllLostItems();
        return ResponseEntity.ok(items);
    }

    // Report new lost item
    @PostMapping
    @Operation(summary = "Report lost item", description = "Report a new lost item to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lost item reported successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<LostItem> addLostItem(
            @Parameter(description = "Lost item details") @RequestBody LostItem lostItem) {
        try {
            LostItem savedItem = service.addLostItem(lostItem);
            return ResponseEntity.ok(savedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

