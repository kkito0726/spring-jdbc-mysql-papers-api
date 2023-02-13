package com.ken.papersapi.services;

import com.ken.papersapi.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<User> findAll() {
    final String query = "SELECT * from user";
    List<User> users = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(User.class)
    );

    return users;
  }
}
