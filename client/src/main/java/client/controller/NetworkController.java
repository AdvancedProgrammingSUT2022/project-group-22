package client.controller;

import client.model.Request;
import client.model.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkController {

    private static Socket socket;
    private static Socket readerSocket;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;

    public static boolean connect(int serverPort) {
        try {
            socket = new Socket("localhost", serverPort);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ignored) {
            ignored.printStackTrace();
            return false;
        }
        return true;
    }

    public static Response send(Request request) {
        try {
            outputStream.writeUTF(request.toJson());
            outputStream.flush();
            return Response.fromJson(inputStream.readUTF());
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    public static void exit() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
