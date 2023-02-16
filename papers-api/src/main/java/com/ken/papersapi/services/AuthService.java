package com.ken.papersapi.services;

import com.ken.papersapi.dtos.LoginUserDto;
import com.ken.papersapi.dtos.UserDto;
import com.ken.papersapi.mappers.UserMapper;
import com.ken.papersapi.models.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final JdbcTemplate jdbcTemplate;
  private final UserMapper _userMapper;

  public UserDto login(LoginUserDto dto) {
    final String query = "SELECT * from users WHERE email=?";
    List<User> users = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(User.class),
      dto.getEmail()
    );
    if (users.isEmpty()) {
      return null;
    } else if (users.get(0).getPassword().equals(dto.getPassword())) {
      return _userMapper.toDto(users.get(0));
    }
    return null;
  }
}
