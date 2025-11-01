
    // FoundItemController.java
package com.campusconnect.controller;

import com.campusconnect.model.FoundItem;
import com.campusconnect.service.LostFoundService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @RestController
    @RequestMapping("/api/found")
    @CrossOrigin(origins = "*")
    public class FoundItemController {

        private final LostFoundService service;
        public FoundItemController(LostFoundService service) { this.service = service; }

        @PostMapping
        public FoundItem addFoundItem(@RequestBody FoundItem foundItem) {
            return service.addFoundItem(foundItem);
        }

        @GetMapping
        public List<FoundItem> getAllFoundItems() {
            return service.getAllFoundItems();
        }
    }
