package com.campusconnect.service;

import com.campusconnect.model.Event;
import com.campusconnect.model.User;
import com.campusconnect.repository.EventRepository;
import com.campusconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get event by ID
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // Create new event
    public Event createEvent(Event event, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            event.setPostedBy(user.get());
            return eventRepository.save(event);
        }
        throw new RuntimeException("User not found with id: " + userId);
    }

    // Update event
    public Event updateEvent(Long id, Event eventDetails, Long userId) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            
            // Check if user is authorized to update (owner or admin)
            if (!event.getPostedBy().getId().equals(userId)) {
                Optional<User> user = userRepository.findById(userId);
                if (!user.isPresent() || !"ADMIN".equals(user.get().getRole())) {
                    throw new RuntimeException("Unauthorized to update this event");
                }
            }
            
            event.setTitle(eventDetails.getTitle());
            event.setDescription(eventDetails.getDescription());
            event.setEventDate(eventDetails.getEventDate());
            event.setLocation(eventDetails.getLocation());
            event.setImageUrl(eventDetails.getImageUrl());
            event.setStatus(eventDetails.getStatus());
            
            return eventRepository.save(event);
        }
        throw new RuntimeException("Event not found with id: " + id);
    }

    // Delete event
    public void deleteEvent(Long id, Long userId) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            
            // Check if user is authorized to delete (owner or admin)
            if (!event.getPostedBy().getId().equals(userId)) {
                Optional<User> user = userRepository.findById(userId);
                if (!user.isPresent() || !"ADMIN".equals(user.get().getRole())) {
                    throw new RuntimeException("Unauthorized to delete this event");
                }
            }
            
            eventRepository.deleteById(id);
        } else {
            throw new RuntimeException("Event not found with id: " + id);
        }
    }

    // Get upcoming events
    public List<Event> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }

    // Get events by status
    public List<Event> getEventsByStatus(String status) {
        return eventRepository.findByStatus(status);
    }

    // Search events by title
    public List<Event> searchEventsByTitle(String keyword) {
        return eventRepository.findByTitleContainingIgnoreCase(keyword);
    }

    // Get events by user
    public List<Event> getEventsByUser(Long userId) {
        return eventRepository.findByPostedByIdOrderByCreatedAtDesc(userId);
    }

    // Get events by location
    public List<Event> getEventsByLocation(String location) {
        return eventRepository.findByLocationContainingIgnoreCase(location);
    }

    // Get events in date range
    public List<Event> getEventsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return eventRepository.findEventsByDateRange(startDate, endDate);
    }
}