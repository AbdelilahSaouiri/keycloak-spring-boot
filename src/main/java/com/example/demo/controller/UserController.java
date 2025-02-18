package com.example.demo.controller;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "**")

public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserResponseDto> addNewUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.addNewUser(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?>  updateUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.updateUser(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?>  consulterUser(@PathVariable(name="userId" ,required = true) String userId){
                     UserResponseDto userResponseDto=userService.consulterUser(userId);
                     return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestParam(name="userId",required = true) String userId){
         userService.deleteUser(userId);
         return ResponseEntity.noContent().build();
    }

}
