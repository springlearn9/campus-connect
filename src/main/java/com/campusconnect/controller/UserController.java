
// UserController.java
package com.campusconnect.controller;

import com.campusconnect.model.User;
import com.campusconnect.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@Tag(name = "Users", description = "User Management API")
public class UserController {

        private final UserService userService;
        public UserController(UserService userService) { this.userService = userService; }

        @PostMapping("/register")
        @Operation(summary = "Register new user", description = "Register a new user in the system")
        public User register(@RequestBody User user) {
            return userService.registerUser(user);
        }

        @GetMapping
        @Operation(summary = "Get all users", description = "Retrieve all registered users")
        public List<User> getAll() {
            return userService.getAllUsers();
        }
    }
