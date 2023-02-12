package com.project.cafearound.domain.user.service;

import com.project.cafearound.domain.user.controller.response.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KakaoOAuth2Service {

  private final WebClient webClient;
  private final String tokenUri = "https://kauth.kakao.com/oauth/token";
  private final String redirectUri = "http://localhost:3000/oauth/kakao/callback";
  private final String grantType = "authorization_code";
  private final String clientId="99710e2765654da6e2b18b853f894c08";

  public KakaoTokenResponse getToken(String code) {
    String uri =
        tokenUri + "?grant_type=" + grantType + "&client_id=" + clientId + "&redirect_uri="
            + redirectUri + "&code=" + code;
    System.out.println(uri);

    KakaoTokenResponse kakaoTokenResponse = webClient.post()
        .uri(uri)
        .contentType(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(KakaoTokenResponse.class)
        .block();

    return kakaoTokenResponse;
  }
}
