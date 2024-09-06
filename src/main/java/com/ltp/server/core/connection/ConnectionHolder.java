package com.ltp.server.core.connection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionHolder {
    private static final List<Connection> connections = new ArrayList<>();

    public static void accept(final Socket socket) {
        final Connection connection = ConnectionFactory.http(socket);
        connections.add(connection);
        connection.perform();
    }

}
