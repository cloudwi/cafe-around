package com.project.cafearound.domain.user.controller;

import com.project.cafearound.domain.user.controller.response.KakaoTokenResponse;
import com.project.cafearound.domain.user.controller.response.KakaoUserInfo;
import com.project.cafearound.domain.user.controller.response.KakaoUserInfoResponse;
import com.project.cafearound.domain.user.controller.response.KakaoOauth2LoginResponseDto;
import com.project.cafearound.domain.user.service.KakaoOAuth2Service;
import com.project.cafearound.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth/callback/kakao")
public class UserController {

  private final KakaoOAuth2Service kakaoOAuth2Service;
  private final KakaoUserInfo kakaoUserInfo;
  private final UserService userService;

  @GetMapping
  public ResponseEntity<KakaoOauth2LoginResponseDto> OAuth2login(@RequestParam("code") String code) {
    KakaoTokenResponse kakaoTokenResponse = kakaoOAuth2Service.getToken(code);
    KakaoUserInfoResponse kakaoUserInfoUserInfo = kakaoUserInfo.getUserInfo(kakaoTokenResponse.getAccess_token());

    KakaoOauth2LoginResponseDto kakaoOauth2LoginResponseDto = userService.OAuth2login(
        kakaoUserInfoUserInfo.getId(), kakaoUserInfoUserInfo.getKakao_account().getProfile().getNickname(), kakaoUserInfoUserInfo.getKakao_account().getProfile().getProfile_image_url());

    return ResponseEntity.ok(kakaoOauth2LoginResponseDto);
  }
}
