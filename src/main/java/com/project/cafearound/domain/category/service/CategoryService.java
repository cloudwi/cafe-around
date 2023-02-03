package com.project.cafearound.domain.category.service;

import com.project.cafearound.domain.cafe.dto.response.CafeSaveResponseDto;
import com.project.cafearound.domain.category.dto.CategoryFindAllResponseDto;
import com.project.cafearound.domain.category.dto.CategorySaveRequestDto;
import com.project.cafearound.domain.category.dto.CategorySaveResponseDto;
import com.project.cafearound.domain.category.entity.Category;
import com.project.cafearound.domain.category.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategorySaveResponseDto save(CategorySaveRequestDto categorySaveRequestDto) {
    Category category = categorySaveRequestDto.toEntity();
    category = categoryRepository.save(category);
    return CategorySaveResponseDto.builder()
        .name(category.getName())
        .build();
  }

  public List<CategoryFindAllResponseDto> findAll() {
    return categoryRepository.findAll().stream()
        .map(CategoryFindAllResponseDto::toDto)
        .toList();
  }
}
