package com.ltp.server.core.http.parser;

import com.ltp.server.core.http.request.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestParser {

    public HttpRequest parse(final Socket socket) throws IOException {
        final HttpRequest request = new HttpRequest();
        final RequestMethodParser requestMethodParser = new RequestMethodParser(request);
        final HeaderParser headerParser = new HeaderParser(request);
        try(final BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final PrintWriter output = new PrintWriter(socket.getOutputStream())) {
            while (true) {
                if (input.ready()) break;
            }
            while (input.ready()) {
                final String line = input.readLine();
                if (!request.isFinishedRequestMethod()) {
                    requestMethodParser.parse(line);
                    request.finishRequestMethod();
                }
                if (!request.isFinishedHeaders()) {
                    if (line.trim().isEmpty()) {
                        request.finishHeaders();
                        continue;
                    }
                    headerParser.parse(line);
                }
            }
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html");
            output.println();
            output.println("<p>Works!</p>");
            output.flush();
        }
        return request;
    }

}
