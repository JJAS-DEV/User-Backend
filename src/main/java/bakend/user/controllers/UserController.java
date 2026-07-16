package bakend.user.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import bakend.user.entities.User;
import bakend.user.services.UserServices;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserServices userService;

    @GetMapping()
    public List<User> list() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.status(404).body(Collections.singletonMap("message", "User not found con el id: " + id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User entity, BindingResult result) {
        // TODO: process POST request

        if (result.hasErrors()) {

            return getErrors(result);
        }

        return ResponseEntity.ok(userService.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User entity, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {

            return getErrors(result);
        }

        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            userToUpdate.setName(entity.getName());
            userToUpdate.setLastname(entity.getLastname());
            userToUpdate.setEmail(entity.getEmail());
            userToUpdate.setPassword(entity.getPassword());
            userToUpdate.setUsername(entity.getUsername());
            return ResponseEntity.ok(userService.save(userToUpdate));
        }
        // TODO: process PUT request
        return ResponseEntity.status(404).body(Collections.singletonMap("message", "User not found with id: " + id));

    }

  

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "User deleted successfully"));
        }
        return ResponseEntity.status(404).body(Collections.singletonMap("message", "User not found with id: " + id));
    }
      private ResponseEntity<?> getErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put("el campo " + error.getField()," "+ error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
