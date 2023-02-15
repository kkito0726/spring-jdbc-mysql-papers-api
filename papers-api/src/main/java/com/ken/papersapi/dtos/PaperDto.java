package com.ken.papersapi.dtos;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperDto {

  private UUID paperId;
  private String title;
  private String comment;
  private UUID userId;
  private LocalDateTime createdAt;
}
