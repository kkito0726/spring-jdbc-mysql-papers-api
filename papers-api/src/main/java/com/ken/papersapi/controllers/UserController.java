package com.ken.papersapi.controllers;

import com.ken.papersapi.dtos.UpdateUserDto;
import com.ken.papersapi.dtos.UserDto;
import com.ken.papersapi.models.User;
import com.ken.papersapi.services.UserService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = UserController.BASE_URL)
@RequiredArgsConstructor
public class UserController {

  private static final String BASE_URL = "/api/v1/users";
  private final UserService userService;

  @GetMapping("")
  public ResponseEntity<List<UserDto>> getUsers() {
    List<UserDto> users = userService.findAll();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUser(@PathVariable UUID userId) {
    UserDto user = userService.findByUserid(userId);
    return ResponseEntity.ok(user);
  }

  @PostMapping("")
  public ResponseEntity<UserDto> createUser(@RequestBody User user) {
    UserDto newUser = userService.save(user);
    return ResponseEntity.ok(newUser);
  }

  @PatchMapping("/{userId}")
  public ResponseEntity<Object> updateUser(
    @RequestBody UpdateUserDto dto,
    @PathVariable UUID userId
  ) {
    userService.updateByUserId(dto, userId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Object> deleteUser(@PathVariable UUID userId) {
    userService.delete(userId);
    return ResponseEntity.noContent().build();
  }
}
