package com.project.cafearound.domain.category.controller.response;

import com.project.cafearound.domain.category.Category;
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
