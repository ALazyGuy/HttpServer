package com.ltp.server.core;

import com.ltp.server.core.connection.ConnectionHolder;
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
            ConnectionHolder.accept(client);
        }
    }

}
