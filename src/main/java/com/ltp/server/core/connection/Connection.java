package com.ltp.server.core.connection;

import lombok.Getter;

import java.net.Socket;

@Getter
public abstract class Connection {
    protected final Socket socket;

    protected Connection(final Socket socket) {
        this.socket = socket;
    }

    public abstract void perform();
}
