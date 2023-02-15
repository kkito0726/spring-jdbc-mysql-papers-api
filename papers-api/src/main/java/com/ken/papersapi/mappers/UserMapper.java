package com.ken.papersapi.mappers;

import com.ken.papersapi.dtos.UserDto;
import com.ken.papersapi.models.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final ModelMapper _modelMapper;

  public UserDto toDto(User entity) {
    return _modelMapper.map(entity, UserDto.class);
  }
}
