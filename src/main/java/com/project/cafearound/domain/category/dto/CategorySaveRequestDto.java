package com.project.cafearound.domain.category.dto;

import com.project.cafearound.domain.category.entity.Category;
import lombok.Builder;

@Builder
public record CategorySaveRequestDto(
    String name
) {
  public Category toEntity() {
    return Category.builder()
        .name(this.name)
        .build();
  }
}
