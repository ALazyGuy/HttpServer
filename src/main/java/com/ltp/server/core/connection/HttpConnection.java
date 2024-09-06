package com.ltp.server.core.connection;

import com.ltp.server.core.http.parser.HeaderParser;
import com.ltp.server.core.http.request.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class HttpConnection extends Connection {

    public HttpConnection(final Socket socket) {
        super(socket);
    }

    @Override
    public void perform() {
        final HttpRequest request = new HttpRequest();
        final HeaderParser headerParser = new HeaderParser(request);
        try(final BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final PrintWriter output = new PrintWriter(socket.getOutputStream())) {
            while (!input.ready()){}
            while (input.ready()) {
                final String line = input.readLine();
                if(!request.isFinishedHeaders()) {
                    if(line.trim().isEmpty()) {
                        request.finishHeaders();
                        continue;
                    }
                    headerParser.parse(line);
                }
            }

            request.getHeaders().forEach((key, value) -> System.out.printf("%s: %s\n", key, value));

            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html");
            output.println();
            output.println("<p>Works!</p>");
            output.flush();
            System.out.printf("Connection id: %s\n", ConnectionHolder.getCurrentConnectionId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
