package com.project.cafearound.domain.category.controller.request;

import com.project.cafearound.domain.category.Category;
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
