package com.project.cafearound.domain.category.controller.response;

import lombok.Builder;

@Builder
public record CategorySaveResponseDto(
    String name
) {

}
