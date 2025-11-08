
// FoundItemController.java
package com.campusconnect.controller;

import com.campusconnect.model.FoundItem;
import com.campusconnect.service.LostFoundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/found")
@CrossOrigin(origins = "*")
@Tag(name = "Found Items", description = "Found Items Management API")
public class FoundItemController {

        private final LostFoundService service;
        public FoundItemController(LostFoundService service) { this.service = service; }

        @PostMapping
        @Operation(summary = "Report found item", description = "Report a new found item to the system")
        public FoundItem addFoundItem(@RequestBody FoundItem foundItem) {
            return service.addFoundItem(foundItem);
        }

        @GetMapping
        @Operation(summary = "Get all found items", description = "Retrieve all reported found items")
        public List<FoundItem> getAllFoundItems() {
            return service.getAllFoundItems();
        }
    }
