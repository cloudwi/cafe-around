package com.project.cafearound.global.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtTokenProvider jwtTokenProvider;

  public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Bean
  public BCryptPasswordEncoder encodePassword() {  // 회원가입 시 비밀번호 암호화에 사용할 Encoder 빈 등록
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring()
        .requestMatchers(PathRequest.toH2Console())
        .requestMatchers("/docs/index.html");
  }
  @Bean
  protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .formLogin().disable()
        .headers().frameOptions().disable()
        .and()
        .cors().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .requestMatchers(HttpMethod.POST, "/api/v1/member/signup").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/v1/member/login").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/v1/post/main").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManagerBean(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
