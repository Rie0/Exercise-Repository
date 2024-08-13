package org.twspring.exerciserepository.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.exerciserepository.Api.ApiResponse;
import org.twspring.exerciserepository.Model.User;
import org.twspring.exerciserepository.Service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    //get
    @GetMapping("/get/all")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.findAllUsers());
    }

    @GetMapping("/get/by_email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(userService.findUserByEmail(email));
    }

    @GetMapping("/get/by_role/{role}")
    public ResponseEntity getUserByRole(@PathVariable String role) {
        return ResponseEntity.status(200).body(userService.findUsersByRole(role));
    }

    @GetMapping("/get/by_age_equals_or_greater/{age}")
    public ResponseEntity getUserByAgeEqualsOrGreater(@PathVariable Integer age) {
        return ResponseEntity.status(200).body(userService.findUsersByAgeEqualsOrGreaterThan(age));
    }

    //v1
    @GetMapping("/check/v1/username_and_password/{username}/{password}")
    public ResponseEntity checkUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        return ResponseEntity.status(200).body(userService.checkUserNameAndPasswordV1(username, password));
    }

    //v2
    @GetMapping("/check/v2/username_and_password/{username}/{password}")
    public ResponseEntity checkUsernameAndPasswordV2(@PathVariable String username, @PathVariable String password) {
        return ResponseEntity.status(200).body(userService.checkUserNameAndPasswordV2(username, password));
    }

    //post
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(201).body(new ApiResponse("User added successfully"));
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
    }
}
