package com.thoughtworks.ssr.common.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private String timestamp;
    private Object data;
    private Boolean success;
    private String cause;
    private String path;

    public ApiResponse(Boolean success, Object data, String cause, String path) {
        this.timestamp = OffsetDateTime.now().toString();
        this.data = data;
        this.success = success;
        this.cause = cause;
        this.path = path;
    }

    public ApiResponse(Boolean success, Object data) {
        this.timestamp = OffsetDateTime.now().toString();
        this.data = data;
        this.success = success;
        this.cause = null;
        this.path = null;
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(true, data);
    }

    public static ApiResponse failed(Object data) {
        return new ApiResponse(false, data, null, null);
    }
    public static ApiResponse failed(Object data, String cause, String path) {
        return new ApiResponse(false, data, cause, path);
    }
}

