package com.saadcodes.techsphere.controller;

import com.saadcodes.techsphere.dtos.UserDto;
import com.saadcodes.techsphere.model.User;
import com.saadcodes.techsphere.request.CreateUserRequest;
import com.saadcodes.techsphere.request.UpdateUserRequest;
import com.saadcodes.techsphere.response.ApiResponse;
import com.saadcodes.techsphere.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.saadcodes.techsphere.dtos.UserDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {

    private final IUserService userService;

    @GetMapping("/email/{email}/user")
    public ResponseEntity<ApiResponse> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        UserDto userDto = userService.UserDto(user);
        return ResponseEntity.ok(new ApiResponse("User retrieved successfully", userDto));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);
        UserDto userDto = userService.UserDto(user);
        return ResponseEntity.ok(new ApiResponse("User created successfully", userDto));
    }

    @GetMapping("/user/{userId}/user")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        UserDto userDto = userService.UserDto(user);
        return ResponseEntity.ok(new ApiResponse("User retrieved successfully", userDto));
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UpdateUserRequest request, @PathVariable Long userId) {
        User updatedUser = userService.updateUser(request, userId);
        UserDto userDto = userService.UserDto(updatedUser);
        return ResponseEntity.ok(new ApiResponse("User updated successfully", userDto));
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse("User deleted successfully", null));
    }
}
