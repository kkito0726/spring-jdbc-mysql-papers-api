package com.ken.papersapi.dtos;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private UUID userId;
  private String name;
  private String email;
  private LocalDateTime createdAt;
}
