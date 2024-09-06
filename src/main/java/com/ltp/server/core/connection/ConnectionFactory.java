package com.ltp.server.core.connection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.Socket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionFactory {

    public static Connection http(final Socket socket) {
        return new HttpConnection(socket);
    }

}
