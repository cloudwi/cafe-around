package com.project.cafearound.domain.category.dto;

import lombok.Builder;

@Builder
public record CategorySaveResponseDto(
    String name
) {

}
