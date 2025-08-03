package com.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main (String[] args) {
        try {
            Socket socket = new Socket("Localhost", 8080);
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = serverReader.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Connection to server lost");
                }
            }).start();

            Scanner consoleScanner = new Scanner(System.in);
            System.out.println("Enter your username");
            String username = consoleScanner.nextLine();
            writer.println(username);
            System.out.println("You can start typing messages");
            while (true) {
                String message = consoleScanner.nextLine();
                writer.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
