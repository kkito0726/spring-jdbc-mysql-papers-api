package com.ken.papersapi.controllers;

import com.ken.papersapi.dtos.LoginUserDto;
import com.ken.papersapi.dtos.UserDto;
import com.ken.papersapi.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = AuthController.BASE_URL)
@RequiredArgsConstructor
public class AuthController {

  private static final String BASE_URL = "api/v1/auth";
  private final AuthService _authService;

  @PostMapping("/login")
  public ResponseEntity<UserDto> login(@RequestBody LoginUserDto dto) {
    UserDto user = _authService.login(dto);
    return ResponseEntity.ok(user);
  }
}
