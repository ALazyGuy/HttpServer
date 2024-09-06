package com.ltp.server.core.http;

import com.ltp.server.core.connection.ConnectionHolder;
import com.ltp.server.core.http.request.HttpRequest;
import com.ltp.server.core.http.request.ResponseStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

@RequiredArgsConstructor
public class ResponseBuilder {
    private final HttpRequest request;
    private final Socket socket;
    @Getter
    @Setter
    private ResponseStatus status;

    public void respond() {
        try{
            final PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.printf("%s %d %s\r\n", request.getProtocol(), status.getCode(), status.name().replaceAll("_", " "));
            output.println("Content-Type: text/html");
            output.println();
            output.println("<p>Works!</p>");
            output.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
