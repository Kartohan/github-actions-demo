package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {
    private static List<ClientHandler> clients = new ArrayList<>();
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String clientUsername;
    public ClientHandler(Socket socket) {
        try {
            this.clientSocket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.clientUsername = reader.readLine();
            clients.add(this);
            broadcastMessage("SERVER: " + clientUsername + " has entered the chat!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(){
        String messageFromClient;
        while (clientSocket.isConnected()) {
            try {
                messageFromClient = reader.readLine();
                if (messageFromClient == null) {
                    closeEverything(clientSocket, reader, writer);
                    break;
                }
                broadcastMessage(clientUsername + ": " + messageFromClient);
            } catch (IOException e) {
                closeEverything(clientSocket, reader, writer);
                break;
            }
        }

    }

    public void broadcastMessage(String message) {
        for (ClientHandler clientHandler : clients) {
            if (!clientHandler.clientUsername.equals(this.clientUsername)) {
                clientHandler.writer.println(message);
            }
        }
    }

    public void removeClientHandler() {
        clients.remove(this);
        broadcastMessage("SERVER: " + clientUsername + " left the chat.");
    }

    public void closeEverything(Socket socket, BufferedReader reader, PrintWriter writer) {
        removeClientHandler();
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
