package de.thephoenixpixel.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TicTacToeGameHandler implements Runnable {
    private TicTacToeServer server;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public TicTacToeGameHandler(TicTacToeServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            writer.println("Welcomme to Tic Tac Toe");

        } finally {
            try {
                reader.close();
                writer.close();
                clientSocket.close();
                server.removeGameHandler(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }
}

