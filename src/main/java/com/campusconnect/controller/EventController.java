package com.campusconnect.controller;

import com.campusconnect.model.Event;
import com.campusconnect.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
@Tag(name = "Events", description = "Campus Events Management API")
public class EventController {

    @Autowired
    private EventService eventService;

    // Get all events
    @GetMapping
    @Operation(summary = "Get all events", description = "Retrieve all campus events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all events"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // Get event by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get event by ID", description = "Retrieve a specific event by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found and returned successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found with the given ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Event> getEventById(
            @Parameter(description = "ID of the event to retrieve") @PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Create new event
    @PostMapping
    @Operation(summary = "Create new event", description = "Create a new campus event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data or user not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Event> createEvent(
            @Parameter(description = "Event details to create") @RequestBody Event event, 
            @Parameter(description = "ID of the user creating the event") @RequestParam Long userId) {
        try {
            Event createdEvent = eventService.createEvent(event, userId);
            return ResponseEntity.ok(createdEvent);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update event
    @PutMapping("/{id}")
    @Operation(summary = "Update event", description = "Update an existing campus event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data or unauthorized"),
            @ApiResponse(responseCode = "404", description = "Event not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Event> updateEvent(
            @Parameter(description = "ID of the event to update") @PathVariable Long id, 
            @Parameter(description = "Updated event details") @RequestBody Event eventDetails, 
            @Parameter(description = "ID of the user updating the event") @RequestParam Long userId) {
        try {
            Event updatedEvent = eventService.updateEvent(id, eventDetails, userId);
            return ResponseEntity.ok(updatedEvent);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete event
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete event", description = "Delete a campus event (only by creator or admin)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Unauthorized or invalid request"),
            @ApiResponse(responseCode = "404", description = "Event not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteEvent(
            @Parameter(description = "ID of the event to delete") @PathVariable Long id, 
            @Parameter(description = "ID of the user deleting the event") @RequestParam Long userId) {
        try {
            eventService.deleteEvent(id, userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get upcoming events
    @GetMapping("/upcoming")
    @Operation(summary = "Get upcoming events", description = "Retrieve all upcoming active events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved upcoming events"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Event>> getUpcomingEvents() {
        List<Event> events = eventService.getUpcomingEvents();
        return ResponseEntity.ok(events);
    }

    // Search events by title
    @GetMapping("/search")
    @Operation(summary = "Search events", description = "Search events by title keyword")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found events matching keyword"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Event>> searchEvents(
            @Parameter(description = "Keyword to search in event titles") @RequestParam String keyword) {
        List<Event> events = eventService.searchEventsByTitle(keyword);
        return ResponseEntity.ok(events);
    }

    // Get events by user
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get events by user", description = "Retrieve all events created by a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user's events"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Event>> getEventsByUser(
            @Parameter(description = "ID of the user") @PathVariable Long userId) {
        List<Event> events = eventService.getEventsByUser(userId);
        return ResponseEntity.ok(events);
    }

    // Get events by location
    @GetMapping("/location")
    @Operation(summary = "Get events by location", description = "Filter events by location")
    public ResponseEntity<List<Event>> getEventsByLocation(
            @Parameter(description = "Location to filter by") @RequestParam String location) {
        List<Event> events = eventService.getEventsByLocation(location);
        return ResponseEntity.ok(events);
    }



    // Get events by date range
    @GetMapping("/date-range")
    @Operation(summary = "Get events by date range", description = "Retrieve events within a specific date range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved events in date range"),
            @ApiResponse(responseCode = "400", description = "Invalid date format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Event>> getEventsByDateRange(
            @Parameter(description = "Start date (ISO 8601 format)") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @Parameter(description = "End date (ISO 8601 format)") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Event> events = eventService.getEventsByDateRange(startDate, endDate);
        return ResponseEntity.ok(events);
    }
}