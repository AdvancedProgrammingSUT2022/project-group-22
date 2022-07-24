package server.controllers;


import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import server.models.Request;
import server.models.Response;
import server.models.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class SocketHandler extends Thread {
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private Request request;

    public SocketHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                request = Request.fromJson(inputStream.readUTF());
                System.out.println("New request from " + socket);
                Response response = handleRequest(request);
                outputStream.writeUTF(response.toJson());
                outputStream.flush();
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    public Request getRequest() {
        return request;
    }

    public void send(String response) {
        try {
            outputStream.writeUTF(response);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) throws IOException {
        Response response = new Response();
        String header = request.getHeader();
        if (header.equals("signup")) {
            return RegisterMenuController.signUp((String) request.getData().get("username"),
                    (String) request.getData().get("nickname"),
                    (String) request.getData().get("password"));
        }
        if (header.equals("login")) {
            return RegisterMenuController.login((String) request.getData().get("username"),
                    (String) request.getData().get("password"));
        }
        if (header.equals("changeNickname")) {
            User user = JwtController.getUser((String) request.getData().get("jwt"));
            return ProfileMenuController.getInstance().changeNickname((String) request.getData().get("newNickname"),
                    user,
                    JwtController.parseJwt((String) request.getData().get("jwt")).getBody().getExpiration());
//            User user = gson.fromJson(json, User.class);
//            System.out.println(json);
//            System.out.println(user.getAvatar());
//            return ProfileMenuController.getInstance().changeNickname((String) request.getData().get("nickname"),
//                    JwtController.parseJwt((String) request.getData().get("jwt")));

        }
        if (header.equals("changePassword")) {
            User user = JwtController.getUser((String) request.getData().get("jwt"));
            return ProfileMenuController.getInstance().changePassword((String) request.getData().get("currentPassword"),
                    (String) request.getData().get("newPassword"),
                    user,
                    JwtController.parseJwt((String) request.getData().get("jwt")).getBody().getExpiration());
        }
        if (header.equals("logout")) {
            ProfileMenuController.getInstance().logout(JwtController.getUser((String) request.getData().get("jwt")));
            response.setStatus(200);
            return response;
        }
        if (header.equals("getAvatar")) {
            response.addData("avatar", JwtController.getUser((String) request.getData().get("jwt")).getAvatar());
            return response;
        }
        if (header.equals("setAvatar")) {
            return ProfileMenuController.getInstance().setAvatar((String) request.getData().get("avatar"),
                    JwtController.getUser((String) request.getData().get("jwt")).getUsername(),
                    JwtController.parseJwt((String) request.getData().get("jwt")).getBody().getExpiration());
        }

        return response;
    }
}
