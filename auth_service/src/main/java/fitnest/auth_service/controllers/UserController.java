package fitnest.auth_service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fitnest.auth_service.clients.EventFeignClient;
import fitnest.auth_service.dto.EventDto;
import fitnest.auth_service.dto.UserDto;
import fitnest.auth_service.entities.User;
import fitnest.auth_service.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final EventFeignClient eventFeignClient;

    private final IUserService userService; // Utilisation de l'interface

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
    // Endpoint to get a user by ID
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

   /* // Vous pouvez ajouter un gestionnaire pour traiter des erreurs de format JSON
    // Ce gestionnaire peut être utilisé si vous souhaitez utiliser un traitement personnalisé dans la logique
    @PostMapping("/addWithCustomJson")
    public ResponseEntity<String> addUserWithCustomJson(@RequestBody String userJson) {
        try {
            // Utilisation de ObjectMapper pour traiter le JSON manuellement
            User user = objectMapper.readValue(userJson, User.class); // Désérialisation manuelle
            userService.addUser(user);
            return ResponseEntity.ok("User added successfully");
        } catch (Exception e) {
            // Si l'erreur JSON survient, renvoyer une réponse d'erreur
            return ResponseEntity.badRequest().body("Invalid JSON format: " + e.getMessage());
        }
    }*/

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
    @GetMapping("/{userId}/events")
    public ResponseEntity<List<EventDto>> getUserEvents(@PathVariable Long userId) {
        try {
            List<EventDto> events = eventFeignClient.getEventsByUserId(userId);
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Nullable
    public ResponseEntity<User> addUser(@NotNull User user) {
        return null;
    }
}