package com.ken.papersapi.models;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paper {

  private int no;

  private UUID paperId;

  private String title;
  private String comment;
  private LocalDateTime createdAt;
  private LocalDateTime deletedAt;
}
