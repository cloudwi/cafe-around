package com.project.cafearound.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionCode {

  BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "요청이 잘못되었습니다."),
  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "멤버를 찾지 못하였습니다"),;

  int status;
  String message;

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
