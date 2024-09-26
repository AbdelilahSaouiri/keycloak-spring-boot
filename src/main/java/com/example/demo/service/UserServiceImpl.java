package com.example.demo.service;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl {

    private final AppUserRepository appUserRepository;

    public UserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<UserResponseDto>  getAllUsers() {
        List<AppUser> all = appUserRepository.findAll();
        List<UserResponseDto> users = new ArrayList<>();
         all.forEach(u->{
             UserResponseDto userResponseDto= new UserResponseDto(u.getUserId(),u.getFirstName(),u.getLastName(),u.getEmail());
             users.add(userResponseDto);
         });
        return users;
    }

    public UserResponseDto addNewUser(UserRequestDto userRequestDto) {
          AppUser user=AppUser.builder()
                  .firstName(userRequestDto.firstName())
                  .lastName(userRequestDto.lastName())
                  .email(userRequestDto.email())
                  .password(userRequestDto.password())
                  .userId(UUID.randomUUID().toString())
                  .build();
        AppUser saved = appUserRepository.save(user);

        return UserResponseDto.builder()
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .build();
    }
}
