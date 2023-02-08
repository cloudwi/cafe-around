package com.project.cafearound.domain.category.controller;

import com.project.cafearound.domain.category.controller.response.CategoryFindAllResponseDto;
import com.project.cafearound.domain.category.controller.request.CategorySaveRequestDto;
import com.project.cafearound.domain.category.controller.response.CategorySaveResponseDto;
import com.project.cafearound.domain.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categorys")
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategorySaveResponseDto> save(
      @RequestBody CategorySaveRequestDto categorySaveRequestDto) {
    CategorySaveResponseDto categorySaveResponseDto = categoryService.save(categorySaveRequestDto);
    return ResponseEntity.ok(categorySaveResponseDto);
  }

  @GetMapping
  public ResponseEntity<List<CategoryFindAllResponseDto>> findAll() {
    List<CategoryFindAllResponseDto> categoryFindAllResponseDto = categoryService.findAll();
    return ResponseEntity.ok(categoryFindAllResponseDto);
  }
}
