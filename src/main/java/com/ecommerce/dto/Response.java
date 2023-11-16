package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response<T> {

    private T data;
    private Status status;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Status {

        private int code;
        private String message;
    }
}
