package com.ltp.server.core.http.request;

import lombok.Getter;

public enum ResponseStatus {
    OK(200), NOT_FOUND(404), FOUND(202);
    @Getter
    private final int code;

    ResponseStatus(int code) {
        this.code = code;
    }
}
