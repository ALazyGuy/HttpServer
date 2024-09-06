package com.ltp.server.core.connection;

import com.ltp.server.core.http.ResponseBuilder;
import com.ltp.server.core.http.parser.HeaderParser;
import com.ltp.server.core.http.parser.RequestMethodParser;
import com.ltp.server.core.http.parser.RequestParser;
import com.ltp.server.core.http.request.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpConnection extends Connection {

    public HttpConnection(final Socket socket) {
        super(socket);
    }

    @Override
    public void perform() {
        try{
            final RequestParser requestParser = new RequestParser();
            final HttpRequest request = requestParser.parse(socket);
            final ResponseBuilder responseBuilder = new ResponseBuilder(request, socket);
            responseBuilder.setCode(200);
            responseBuilder.respond();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
