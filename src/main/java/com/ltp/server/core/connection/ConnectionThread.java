package com.ltp.server.core.connection;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectionThread implements Runnable {

    private final String id;
    private final Connection connection;

    @Override
    public void run(){
        try{
            ConnectionHolder.associateThread(id);
            connection.perform();
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
        ConnectionHolder.removeSelf(id);
    }
}
