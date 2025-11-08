
// LostItemController.java
package com.campusconnect.controller;

import com.campusconnect.model.LostItem;
import com.campusconnect.service.LostFoundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lost")
@CrossOrigin(origins = "*")
@Tag(name = "Lost Items", description = "Lost Items Management API")
public class LostItemController {

        private final LostFoundService service;
        public LostItemController(LostFoundService service) { this.service = service; }

        @PostMapping
        @Operation(summary = "Report lost item", description = "Report a new lost item to the system")
        public LostItem addLostItem(@RequestBody LostItem lostItem) {
            return service.addLostItem(lostItem);
        }

        @GetMapping
        @Operation(summary = "Get all lost items", description = "Retrieve all reported lost items")
        public List<LostItem> getAllLostItems() {
            return service.getAllLostItems();
        }
    }

