package com.ltp.server.core;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler {

    @Getter
    private final int port;
    private final ServerSocket serverSocket;

    public ConnectionHandler(final int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    public void test() throws IOException {
        while (true) {
            final Socket client = serverSocket.accept();
            try(final BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                final PrintWriter output = new PrintWriter(client.getOutputStream())) {
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
            }
        }
    }

}
