package com.woohuhu.spring_backend_auth.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Response<T> {
    private int statusCode;
    private String message;
    private T data;

    public Response(final int statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = null;
    }

    public static <T> Response<T> response(final int statusCode, final String message) {
        return response(statusCode, message, null);
    }

    public static <T> Response<T> response(final int statusCode, final String message, final T data) {
        return Response.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .data(data)
                .build();
    }
}
