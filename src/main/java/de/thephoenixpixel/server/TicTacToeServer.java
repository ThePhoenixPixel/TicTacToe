package de.thephoenixpixel.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeServer {
    private static final int PORT = 12345;
    private List<TicTacToeGameHandler> gameHandlers = new ArrayList<>();

    public static void main(String[] args) {
        new TicTacToeServer().startServer();
    }

    private void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                TicTacToeGameHandler gameHandler = new TicTacToeGameHandler(this, clientSocket);
                gameHandlers.add(gameHandler);
                new Thread(gameHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String message) {
        for (TicTacToeGameHandler handler : gameHandlers) {
            handler.sendMessage(message);
        }
    }

    public void removeGameHandler(TicTacToeGameHandler gameHandler) {
        gameHandlers.remove(gameHandler);
    }
}
