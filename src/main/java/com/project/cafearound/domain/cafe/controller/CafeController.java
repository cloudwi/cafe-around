package com.project.cafearound.domain.cafe.controller;

import com.project.cafearound.domain.cafe.controller.request.CafeSaveRequestDto;
import com.project.cafearound.domain.cafe.controller.response.CafeSaveResponseDto;
import com.project.cafearound.domain.cafe.service.CafeService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cafes")
public class CafeController {

  private final CafeService cafeService;

  @PostMapping
  public ResponseEntity<CafeSaveResponseDto> save(
      @RequestBody @Valid CafeSaveRequestDto cafeSaveRequestDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new IllegalArgumentException("잘못된 요청입니다.");
    }
    CafeSaveResponseDto cafeSaveResponseDto = cafeService.save(cafeSaveRequestDto);
    return ResponseEntity.ok(cafeSaveResponseDto);
  }

  @GetMapping
  public ResponseEntity<List<CafeSaveResponseDto>> findAll() {
    List<CafeSaveResponseDto> cafeSaveResponseDtos = cafeService.findAll();
    return ResponseEntity.ok(cafeSaveResponseDtos);
  }
}
