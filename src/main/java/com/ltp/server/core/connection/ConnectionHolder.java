package com.ltp.server.core.connection;

import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionHolder {
    private static final Map<String, Connection> connections = new HashMap<>();
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void accept(final Socket socket) {
        final Connection connection = ConnectionFactory.http(socket);
        final String id = saveConnection(connection);
        final ConnectionThread connectionThread = new ConnectionThread(id, connection);
        executor.submit(connectionThread);
    }

    public static synchronized void removeSelf(final String id) {
        connections.remove(id);
        System.out.printf("Removed connection %s\n", id);
    }

    private static String saveConnection(final Connection connection) {
        String id = "";

        do {
            id = UUID.randomUUID().toString();
        } while(connections.containsKey(id));

        connections.put(id, connection);
        System.out.printf("Saved connection: %s\n", id);
        return id;
    }

}
