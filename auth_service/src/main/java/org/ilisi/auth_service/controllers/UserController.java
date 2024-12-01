package org.ilisi.auth_service.controllers;

import org.ilisi.auth_service.dto.ErrorResponse;
import org.ilisi.auth_service.dto.UserDto;
import org.ilisi.auth_service.entities.User;
import org.ilisi.auth_service.services.IUserService; // Import de l'interface
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private IUserService userService; // Utilisation de l'interface

    // Injection de ObjectMapper pour gérer la désérialisation JSON
    @Autowired
    private ObjectMapper objectMapper;

    // Endpoint to add a new user
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserDto user) {
        // Vous pouvez aussi vérifier ou traiter des erreurs JSON ici
        User createdUser = userService.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        Optional<User> user = userService.getUser(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Endpoint to get a user's interests
    @GetMapping("/{userId}/interests")
    public ResponseEntity<List<String>> getUserInterests(@PathVariable Long userId) {
        List<String> interests = userService.getUserInterests(userId);
        return ResponseEntity.ok(interests);
    }


    // Endpoint pour mettre à jour un utilisateur
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        try {
            User updated = userService.updateUser(userId, updatedUser);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{userId}/username")
    public ResponseEntity<String> getUserNameById(@PathVariable Long userId) {
        Optional<String> username = userService.getUserNameById(userId);
        return username.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Nullable
    public ResponseEntity<User> addUser(@NotNull User user) {
        return null;
    }
}