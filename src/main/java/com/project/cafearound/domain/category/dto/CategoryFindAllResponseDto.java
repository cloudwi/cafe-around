package com.project.cafearound.domain.category.dto;

import com.project.cafearound.domain.cafe.entity.Cafe;
import com.project.cafearound.domain.category.entity.Category;
import lombok.Builder;

@Builder
public record CategoryFindAllResponseDto(
    String name
) {
  public static CategoryFindAllResponseDto toDto(Category category) {
    return CategoryFindAllResponseDto.builder()
        .name(category.getName())
        .build();
  }
}
