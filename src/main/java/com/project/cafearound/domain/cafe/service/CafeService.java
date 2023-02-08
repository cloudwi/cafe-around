package com.project.cafearound.domain.cafe.service;

import com.project.cafearound.domain.cafe.controller.request.CafeSaveRequestDto;
import com.project.cafearound.domain.cafe.controller.response.CafeSaveResponseDto;
import com.project.cafearound.domain.cafe.Cafe;
import com.project.cafearound.domain.cafe.repository.CafeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CafeService {

  private final CafeRepository cafeRepository;

  public CafeSaveResponseDto save(CafeSaveRequestDto cafeSaveRequestDto) {
    Cafe cafe = cafeSaveRequestDto.toEntity();

    cafe = cafeRepository.save(cafe);

    return CafeSaveResponseDto.toDto(cafe);
  }

  public List<CafeSaveResponseDto> findAll() {
    return cafeRepository.findAll().stream()
        .map(CafeSaveResponseDto::toDto)
        .toList();
  }
}
