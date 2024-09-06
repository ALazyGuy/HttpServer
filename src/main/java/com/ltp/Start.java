package com.ltp;

import com.ltp.server.core.ConnectionHandler;

import java.io.IOException;

public class Start {

    public static void main(String[] args) throws IOException {
        final ConnectionHandler connectionHandler = new ConnectionHandler(8080);
        connectionHandler.test();
    }

}
