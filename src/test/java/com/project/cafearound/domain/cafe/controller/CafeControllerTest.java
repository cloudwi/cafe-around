package com.project.cafearound.domain.cafe.controller;

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
import com.project.cafearound.domain.cafe.controller.request.CafeSaveRequestDto;
import com.project.cafearound.domain.cafe.service.CafeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class CafeControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  CafeService cafeService;
  @Autowired
  ObjectMapper objectMapper;

  @Test
  @DisplayName("카페 save api rest docs")

  void save() throws Exception {
    //given
    String cafeName = "부산대 파스쿠찌";
    double latitude = 123.11254;
    double longitude = 35.78546;

    CafeSaveRequestDto cafeSaveRequestDto = CafeSaveRequestDto.builder()
        .cafeName(cafeName)
        .latitude(latitude)
        .longitude(longitude)
        .build();

    String json = objectMapper.writeValueAsString(cafeSaveRequestDto);

    //when
    //then
    mockMvc.perform(
            post("/api/v1/cafes")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json)
        )
        .andExpect(status().isOk())
        .andDo(
            document(
                "CafeController/save",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("cafeName").type(JsonFieldType.STRING).description("등록할 카페 이름"),
                    fieldWithPath("latitude").type(JsonFieldType.NUMBER).description("등록할 카페의 위도"),
                    fieldWithPath("longitude").type(JsonFieldType.NUMBER).description("등록할 카페의 경도")
                ),
                responseFields(
                    fieldWithPath("cafeName").type(JsonFieldType.STRING).description("등록할 카페 이름"),
                    fieldWithPath("latitude").type(JsonFieldType.NUMBER).description("등록할 카페의 위도"),
                    fieldWithPath("longitude").type(JsonFieldType.NUMBER).description("등록할 카페의 경도")
                )
            )
        );
  }

  @Test
  @DisplayName("cafe findAll api rest docs")
  void findAll() throws Exception {
    mockMvc.perform(
            get("/api/v1/cafes")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
        )
        .andExpect(status().isOk())
        .andDo(
            document(
                "CafeController/findAll",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("[].cafeName").type(JsonFieldType.STRING)
                        .description("조회한 카페 이름").optional(),
                    fieldWithPath("[].latitude").type(JsonFieldType.NUMBER)
                        .description("조회한 카페 위도").optional(),
                    fieldWithPath("[].longitude").type(JsonFieldType.NUMBER)
                        .description("조회한 카페 경도").optional()
                )
            )
        );
  }
}