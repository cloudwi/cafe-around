package com.project.cafearound.domain.user.service;

import com.project.cafearound.domain.user.controller.response.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KakaoOAuth2Service {

  private final WebClient webClient;
  @Value("${spring.security.oauth2.client.provider.kakao.token_uri}")
  private final String tokenUri;
  @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
  private final String redirectUri;
  @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
  private final String grantType;
  @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
  private final String clientId;

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
