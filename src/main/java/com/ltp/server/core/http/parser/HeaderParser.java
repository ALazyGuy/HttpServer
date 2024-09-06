package com.ltp.server.core.http.parser;

import com.ltp.server.core.http.request.HttpRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HeaderParser {

    private final HttpRequest request;

    public void parse(final String header) {
        final int headerNameEnd = header.indexOf(':');
        if (headerNameEnd != -1) {
            final String headerName = header.substring(0, headerNameEnd).trim();
            final String headerValue = header.substring(headerNameEnd + 1).trim();
            request.addHeader(headerName, headerValue);
        }
    }

}
