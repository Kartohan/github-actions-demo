package com.example;

import java.net.ServerSocket;

public class OldFeatures extends App {
    private static final ServerSocket serverSocket = null;

    public OldFeatures() {
        super(serverSocket);
    }

    public String greet(String name) {
            if (name == null || name.trim().isEmpty()) {
                return "Hello, World!";
            }
            return "Hello, " + name + "!";
        }

}
