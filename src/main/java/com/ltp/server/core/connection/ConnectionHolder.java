package com.ltp.server.core.connection;

import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionHolder {
    private static final Map<String, Connection> connections = new ConcurrentHashMap<>();
    private static final Map<Long, String> threads = new ConcurrentHashMap<>();
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void accept(final Socket socket) {
        final Connection connection = ConnectionFactory.http(socket);
        final String id = saveConnection(connection);
        final ConnectionThread connectionThread = new ConnectionThread(id, connection);
        executor.submit(connectionThread);
    }

    public static synchronized void removeSelf(final String id) {
        connections.remove(id);
    }

    public static synchronized Connection getCurrentConnection() {
        final String connectionId = threads.get(Thread.currentThread().threadId());
        if(connectionId == null) {
            return null;
        }
        return connections.get(connectionId);
    }

    public static synchronized String getCurrentConnectionId() {
        return threads.get(Thread.currentThread().threadId());
    }

    public static void associateThread(final String connectionId) {
        threads.put(Thread.currentThread().threadId(), connectionId);
    }

    private static String saveConnection(final Connection connection) {
        String id = "";

        do {
            id = UUID.randomUUID().toString();
        } while(connections.containsKey(id));

        connections.put(id, connection);
        return id;
    }

}
