// LostFoundService.java
package com.campusconnect.service;

import com.campusconnect.model.LostItem;
import com.campusconnect.model.FoundItem;
import com.campusconnect.repository.LostItemRepository;
import com.campusconnect.repository.FoundItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LostFoundService {
    private final LostItemRepository lostRepo;
    private final FoundItemRepository foundRepo;

    public LostFoundService(LostItemRepository lostRepo, FoundItemRepository foundRepo) {
        this.lostRepo = lostRepo;
        this.foundRepo = foundRepo;
    }

    public LostItem addLostItem(LostItem lostItem) {
        return lostRepo.save(lostItem);
    }

    public FoundItem addFoundItem(FoundItem foundItem) {
        return foundRepo.save(foundItem);
    }

    public List<LostItem> getAllLostItems() {
        return lostRepo.findAll();
    }

    public List<FoundItem> getAllFoundItems() {
        return foundRepo.findAll();
    }
}

