package com.ken.papersapi.mappers;

import com.ken.papersapi.dtos.PaperDto;
import com.ken.papersapi.models.Paper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaperMapper {

  private final ModelMapper _modelMapper;

  public PaperDto toDto(Paper entity) {
    return _modelMapper.map(entity, PaperDto.class);
  }
}
