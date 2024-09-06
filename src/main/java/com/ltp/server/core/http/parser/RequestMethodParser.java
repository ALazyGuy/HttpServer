package com.ltp.server.core.http.parser;

import com.ltp.server.core.http.request.HttpRequest;
import com.ltp.server.core.http.request.RequestMethod;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestMethodParser {
    private final HttpRequest request;

    public void parse(final String requestMethodLine) {
        final String[] parts = requestMethodLine.trim().split("\\s");
        final RequestMethod requestMethod = RequestMethod.valueOf(parts[0]);
        request.setRequestMethod(requestMethod);
        request.setRequestPath(parts[1]);
        request.setProtocol(parts[2]);
    }

}
