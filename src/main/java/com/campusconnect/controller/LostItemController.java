
    // LostItemController.java
package com.campusconnect.controller;

import com.campusconnect.model.LostItem;
import com.campusconnect.service.LostFoundService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @RestController
    @RequestMapping("/api/lost")
    @CrossOrigin(origins = "*")
    public class LostItemController {

        private final LostFoundService service;
        public LostItemController(LostFoundService service) { this.service = service; }

        @PostMapping
        public LostItem addLostItem(@RequestBody LostItem lostItem) {
            return service.addLostItem(lostItem);
        }

        @GetMapping
        public List<LostItem> getAllLostItems() {
            return service.getAllLostItems();
        }
    }

