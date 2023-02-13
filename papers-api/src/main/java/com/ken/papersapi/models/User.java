package com.ken.papersapi.models;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private int no;
  private UUID userId;
  private String name;
  private String email;
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime deletedAt;
}
