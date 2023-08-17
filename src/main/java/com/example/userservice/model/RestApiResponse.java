package com.example.userservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@Data
@Builder
public class RestApiResponse {

    private int statusCode;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object details;

    public static ResponseEntity<Object> buildResponseWithDetails(int statusCode, String message, Object details) {
        return new ResponseEntity<>(RestApiResponse.builder()
                .statusCode(statusCode)
                .details(details)
                .message(message).build()
                , HttpStatus.valueOf(statusCode));
    }

    public static ResponseEntity<Object> buildResponseWithOutDetails(int statusCode, String message) {
        return new ResponseEntity<>(RestApiResponse.builder()
                .statusCode(statusCode)
                .message(message).build()
                , HttpStatus.valueOf(statusCode));
    }
}
