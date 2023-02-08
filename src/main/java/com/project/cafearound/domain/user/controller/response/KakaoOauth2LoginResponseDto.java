package com.project.cafearound.domain.user.controller.response;

import lombok.Builder;

@Builder
public record KakaoOauth2LoginResponseDto(
    String accessToken
) {


}
