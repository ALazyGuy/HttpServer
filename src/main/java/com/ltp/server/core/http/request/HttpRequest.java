package com.ltp.server.core.http.request;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class HttpRequest {
    private final Map<String, String> headers;
    private boolean finishedHeaders = false;

    public HttpRequest() {
        headers = new HashMap<>();
    }

    public boolean hasHeader(final String name) {
        return headers.containsKey(name);
    }

    public String getHeader(final String name) {
        return headers.get(name);
    }

    public void addHeader(final String name, final String value) {
        headers.put(name, value);
    }

    public void finishHeaders() {
        finishedHeaders = true;
    }
}
