package com.ssafy.Tteonaso.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ssafy.Tteonaso.apiPayload.code.status.SuccessStatus;
import com.ssafy.Tteonaso.web.dto.ChatRoomResponseDTO;
import com.ssafy.Tteonaso.web.dto.GptEnumResponseDTO;
import com.ssafy.Tteonaso.web.dto.GptTravelResponseDTO;
import com.ssafy.Tteonaso.web.dto.MapFilterResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
@Schema(description = "공통 API 응답 객체")
public class ApiResponseDTO<T> {
    @JsonProperty("isSuccess")
    @Schema(description = "응답 성공 여부", example = "true")
    private final Boolean isSuccess;

    @Schema(description = "응답 코드", example = "200")
    private final String code;

    @Schema(description = "응답 메시지", example = "요청이 성공적으로 처리되었습니다.")
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "응답 데이터")
    private T result;


    public static <T> ApiResponseDTO<T> onSuccess(T result) {
        return new ApiResponseDTO<>(true, SuccessStatus.OK.getCode(), SuccessStatus.OK.getMessage(), result);
    }

    public static <T> ApiResponseDTO<T> onFailure(String code, String message, T data) {
        return new ApiResponseDTO<>(false, code, message, data);
    }
}
