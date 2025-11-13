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
    @Operation(summary = "Get notice by ID", description = "Retrieve a specific notice by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notice found and returned successfully"),
            @ApiResponse(responseCode = "404", description = "Notice not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Notice> getNoticeById(
            @Parameter(description = "ID of the notice to retrieve") @PathVariable Long id) {
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
    @Operation(summary = "Update notice", description = "Update an existing campus notice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notice updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data or unauthorized"),
            @ApiResponse(responseCode = "404", description = "Notice not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Notice> updateNotice(
            @Parameter(description = "ID of the notice to update") @PathVariable Long id, 
            @Parameter(description = "Updated notice details") @RequestBody Notice noticeDetails, 
            @Parameter(description = "ID of the user updating the notice") @RequestParam Long userId) {
        try {
            Notice updatedNotice = noticeService.updateNotice(id, noticeDetails, userId);
            return ResponseEntity.ok(updatedNotice);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete notice
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete notice", description = "Delete a campus notice (only by creator or admin)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notice deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Unauthorized or invalid request"),
            @ApiResponse(responseCode = "404", description = "Notice not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteNotice(
            @Parameter(description = "ID of the notice to delete") @PathVariable Long id, 
            @Parameter(description = "ID of the user deleting the notice") @RequestParam Long userId) {
        try {
            noticeService.deleteNotice(id, userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get active notices
    @GetMapping("/active")
    @Operation(summary = "Get active notices", description = "Retrieve all currently active notices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved active notices"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Notice>> getActiveNotices() {
        List<Notice> notices = noticeService.getActiveNotices();
        return ResponseEntity.ok(notices);
    }

    // Get notices by status
    @GetMapping("/status/{status}")
    @Operation(summary = "Get notices by status", description = "Filter notices by their status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved notices by status"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Notice>> getNoticesByStatus(
            @Parameter(description = "Status to filter by") @PathVariable String status) {
        List<Notice> notices = noticeService.getNoticesByStatus(status);
        return ResponseEntity.ok(notices);
    }

    // Get notices by category
    @GetMapping("/category/{category}")
    @Operation(summary = "Get notices by category", description = "Filter notices by category (ACADEMIC, ADMINISTRATIVE, EVENT, GENERAL)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved notices by category"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Notice>> getNoticesByCategory(
            @Parameter(description = "Category to filter by") @PathVariable String category) {
        List<Notice> notices = noticeService.getNoticesByCategory(category);
        return ResponseEntity.ok(notices);
    }

    // Get notices by priority
    @GetMapping("/priority/{priority}")
    @Operation(summary = "Get notices by priority", description = "Filter notices by priority level (HIGH, NORMAL, LOW)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved notices by priority"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Notice>> getNoticesByPriority(
            @Parameter(description = "Priority level to filter by") @PathVariable String priority) {
        List<Notice> notices = noticeService.getNoticesByPriority(priority);
        return ResponseEntity.ok(notices);
    }

    // Search notices by title
    @GetMapping("/search")
    @Operation(summary = "Search notices", description = "Search notices by title keyword")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found notices matching keyword"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Notice>> searchNotices(
            @Parameter(description = "Keyword to search in notice titles") @RequestParam String keyword) {
        List<Notice> notices = noticeService.searchNoticesByTitle(keyword);
        return ResponseEntity.ok(notices);
    }

    // Get notices by user
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get notices by user", description = "Retrieve all notices posted by a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user's notices"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Notice>> getNoticesByUser(
            @Parameter(description = "ID of the user") @PathVariable Long userId) {
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