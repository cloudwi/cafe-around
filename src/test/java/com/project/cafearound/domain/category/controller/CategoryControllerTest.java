package com.project.cafearound.domain.category.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.cafearound.domain.category.dto.CategorySaveRequestDto;
import com.project.cafearound.domain.category.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class CategoryControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  CategoryService categoryService;

  @Test
  @DisplayName("category save api rest docs")
  void save() throws Exception {
    String categoryName = "책상 넓음";

    CategorySaveRequestDto categorySaveRequestDto = CategorySaveRequestDto.builder()
        .name(categoryName)
        .build();

    String json = objectMapper.writeValueAsString(categorySaveRequestDto);

    mockMvc.perform(
            post("/api/v1/categorys")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json)
        )
        .andExpect(status().isOk())
        .andDo(
            document(
                "CategoryController/save",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("name").type(JsonFieldType.STRING).description("등록할 카테고리 이름")
                    ),
                responseFields(
                    fieldWithPath("name").type(JsonFieldType.STRING).description("등록한 카테고리 이름")
                )
            )
        );
  }

  @Test
  @DisplayName("category findAll api rest docs")
  void findAll() throws Exception {
    mockMvc.perform(
            get("/api/v1/categorys")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
        )
        .andExpect(status().isOk())
        .andDo(
            document(
                "CategoryController/findAll",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("[].name").type(JsonFieldType.STRING).description("등록된 카테고리 이름").optional()
                )
            )
        );
  }
}