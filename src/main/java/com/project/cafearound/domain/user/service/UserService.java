package com.project.cafearound.domain.user.service;

import com.project.cafearound.domain.user.Role;
import com.project.cafearound.domain.user.User;
import com.project.cafearound.domain.user.controller.response.KakaoOauth2LoginResponseDto;
import com.project.cafearound.domain.user.repository.UserRepository;
import com.project.cafearound.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;

  public KakaoOauth2LoginResponseDto OAuth2login(Long id, String nickname, String profileImageUrl) {

    User user = User.builder()
        .nickname(nickname)
        .profileImageUrl(profileImageUrl)
        .build();

    user = userRepository.save(user);

    String accessToken = jwtTokenProvider.createAccessToken(user.getId(), Role.ROLE_USER);

    return KakaoOauth2LoginResponseDto.builder()
        .accessToken(accessToken)
        .build();
  }
}
