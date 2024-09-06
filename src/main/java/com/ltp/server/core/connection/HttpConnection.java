package com.ltp.server.core.connection;

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
        try(final BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final PrintWriter output = new PrintWriter(socket.getOutputStream())) {
            while (!input.ready()){}
            System.out.println();
            while (input.ready()) {
                final String line = input.readLine();
                System.out.println(line);
            }

            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html");
            output.println();
            output.println("<p>Works!</p>");
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
