package com.ken.papersapi.services;

import com.ken.papersapi.dtos.UpdateUserDto;
import com.ken.papersapi.dtos.UserDto;
import com.ken.papersapi.mappers.UserMapper;
import com.ken.papersapi.models.Follow;
import com.ken.papersapi.models.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final JdbcTemplate jdbcTemplate;
  private final UserMapper _userMapper;

  public List<UserDto> findAll() {
    final String query = "SELECT * from users WHERE deleted_at is NULL";
    List<User> users = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(User.class)
    );
    List<UserDto> userDtos = users
      .stream()
      .map(userItem -> _userMapper.toDto(userItem))
      .toList();

    return userDtos;
  }

  public UserDto findByUserid(UUID userId) {
    final String query = "SELECT * from users WHERE user_id=?";
    List<User> users = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(User.class),
      userId.toString()
    );

    return _userMapper.toDto(users.get(0));
  }

  public void updateByUserId(UpdateUserDto dto, UUID userId) {
    final String query =
      "UPDATE users SET name=?, email=?, password=? WHERE user_id=?";

    jdbcTemplate.update(
      query,
      dto.getName(),
      dto.getEmail(),
      dto.getPassword(),
      userId.toString()
    );
  }

  public void delete(UUID userId) {
    final String query = "UPDATE users SET deleted_at=? WHERE user_id=?";
    jdbcTemplate.update(query, LocalDateTime.now(), userId.toString());
  }

  public void follow(Follow follow) {
    SqlParameterSource param = new BeanPropertySqlParameterSource(follow);
    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
      .withTableName("follows");
    insert.execute(param);
  }

  public List<Follow> getFollowsByFollowingUserId(UUID followingUserId) {
    final String query = "SELECT * FROM follows WHERE following_user_id=?";
    List<Follow> follows = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(),
      followingUserId.toString()
    );
    return follows;
  }

  public List<Follow> getFollowsByFollowedUserId(UUID followedUserId) {
    final String query = "SELECT * FROM follows WHERE followed_user_id=?";
    List<Follow> follows = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(),
      followedUserId.toString()
    );
    return follows;
  }

  public UserDto save(User user) {
    onCreateUser(user);
    SqlParameterSource param = new BeanPropertySqlParameterSource(user);
    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
      .withTableName("users")
      .usingGeneratedKeyColumns("no");

    Number key = insert.executeAndReturnKey(param);
    user.setNo(key.hashCode());

    return _userMapper.toDto(user);
  }

  public void onCreateUser(User user) {
    user.setUserId(UUID.randomUUID());
    user.setCreatedAt(LocalDateTime.now());
  }
}
