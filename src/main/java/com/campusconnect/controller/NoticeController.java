package com.campusconnect.controller;

import com.campusconnect.model.Notice;
import com.campusconnect.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notices")
@CrossOrigin(origins = "*")
@Tag(name = "Notices", description = "Campus Notices Management API")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // Get all notices
    @GetMapping
    @Operation(summary = "Get all notices", description = "Retrieve all campus notices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all notices"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Notice>> getAllNotices() {
        List<Notice> notices = noticeService.getAllNotices();
        return ResponseEntity.ok(notices);
    }

    // Get notice by ID
    @GetMapping("/{id}")
    public ResponseEntity<Notice> getNoticeById(@PathVariable Long id) {
        Optional<Notice> notice = noticeService.getNoticeById(id);
        return notice.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Create new notice
    @PostMapping
    @Operation(summary = "Create new notice", description = "Create a new campus notice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notice created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data or user not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Notice> createNotice(
            @Parameter(description = "Notice details to create") @RequestBody Notice notice, 
            @Parameter(description = "ID of the user creating the notice") @RequestParam Long userId) {
        try {
            Notice createdNotice = noticeService.createNotice(notice, userId);
            return ResponseEntity.ok(createdNotice);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update notice
    @PutMapping("/{id}")
    public ResponseEntity<Notice> updateNotice(@PathVariable Long id, 
                                             @RequestBody Notice noticeDetails, 
                                             @RequestParam Long userId) {
        try {
            Notice updatedNotice = noticeService.updateNotice(id, noticeDetails, userId);
            return ResponseEntity.ok(updatedNotice);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete notice
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id, @RequestParam Long userId) {
        try {
            noticeService.deleteNotice(id, userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get active notices
    @GetMapping("/active")
    public ResponseEntity<List<Notice>> getActiveNotices() {
        List<Notice> notices = noticeService.getActiveNotices();
        return ResponseEntity.ok(notices);
    }

    // Get notices by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Notice>> getNoticesByStatus(@PathVariable String status) {
        List<Notice> notices = noticeService.getNoticesByStatus(status);
        return ResponseEntity.ok(notices);
    }

    // Get notices by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Notice>> getNoticesByCategory(@PathVariable String category) {
        List<Notice> notices = noticeService.getNoticesByCategory(category);
        return ResponseEntity.ok(notices);
    }

    // Get notices by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Notice>> getNoticesByPriority(@PathVariable String priority) {
        List<Notice> notices = noticeService.getNoticesByPriority(priority);
        return ResponseEntity.ok(notices);
    }

    // Search notices by title
    @GetMapping("/search")
    public ResponseEntity<List<Notice>> searchNotices(@RequestParam String keyword) {
        List<Notice> notices = noticeService.searchNoticesByTitle(keyword);
        return ResponseEntity.ok(notices);
    }

    // Get notices by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notice>> getNoticesByUser(@PathVariable Long userId) {
        List<Notice> notices = noticeService.getNoticesByUser(userId);
        return ResponseEntity.ok(notices);
    }

    // Get high priority notices
    @GetMapping("/high-priority")
    @Operation(summary = "Get high priority notices", description = "Retrieve all active high priority notices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved high priority notices"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Notice>> getHighPriorityNotices() {
        List<Notice> notices = noticeService.getHighPriorityNotices();
        return ResponseEntity.ok(notices);
    }
}