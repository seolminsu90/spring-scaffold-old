package com.scaffold.api.controller;

import com.scaffold.api.dto.UserDTO;
import com.scaffold.api.model.ApiResponse;
import com.scaffold.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> getUser(@PathVariable long userId) {
        var user1 = userService.getUserByJpa(userId);

        return ResponseEntity.ok(ApiResponse.success(user1));
    }


    @GetMapping("/users/{userId}/queryDsl")
    public ResponseEntity<ApiResponse<UserDTO>> getUser2(@PathVariable long userId) {
        var user2 = userService.getUserByQueryDSL(userId);

        return ResponseEntity.ok(ApiResponse.success(user2));
    }
}
