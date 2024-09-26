package com.example.demo.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(String userId,String firstName,String lastName,String email) {
}
