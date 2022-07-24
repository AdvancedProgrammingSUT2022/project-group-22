package server;

import server.controllers.SocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class Main {
    public static void main(String[] args) throws IOException {
//        User user = new User("s","s","g","f",null,0,null, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM d uuuu, HH:mm:ss")));
//        JwtController.parseJwt(JwtController.createJWT(user));
        final int SERVER_PORT = 5000;
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("Listening on port " + SERVER_PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New connection made");
            SocketHandler handler = new SocketHandler(socket);
            handler.start();
        }
    }
}