package client.views;

import client.App;
import client.controller.NetworkController;
import client.model.Request;
import client.model.Response;
import client.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScoreboardPage extends Menu {
    private static ScoreboardPage instance;
    private BorderPane pane;
    private GridPane bgGrid;
    private GridPane mainGrid;

    public static ScoreboardPage getInstance() {
        return instance != null ? instance : new ScoreboardPage();
    }

    public BorderPane getPane() {
        return this.pane;
    }

    private ScoreboardPage() {
        pane = new BorderPane();
        Scene scene = new Scene(pane, 1280, 800);
        pane.setBackground(new Background(backgroundImage));
        addElements();
    }

    public void addElements() {
        addTitle();
        addBackButton();

        Rectangle rectangle = new Rectangle(900, 550);
        rectangle.setArcWidth(30);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.web("#00224A", 0.8));

        bgGrid = new GridPane();
        bgGrid.setAlignment(Pos.CENTER);
        bgGrid.setVgap(10);

        mainGrid = new GridPane();
        mainGrid.setAlignment(Pos.CENTER);
        mainGrid.setHgap(70);
        mainGrid.setVgap(14);

        try {
            this.listenForUpdates();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StackPane centerPane = new StackPane();
        centerPane.getChildren().addAll(rectangle, bgGrid, mainGrid);

        pane.setCenter(centerPane);
    }

    public void addTitle() {
        Text title = createText("scoreboard");
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title, new Insets(70, 0, 0, 0));
        pane.setTop(title);
    }

    public void addBackButton() {
        Button backButton = createSwitchSceneButton("back", MainMenu.getInstance().getPane().getScene());
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(0, 0, 20, 0));
        pane.setBottom(backButton);
    }

    public void addRatings(ArrayList<UserView> users) {
        int i = 1;
        for (UserView user : users) {
            ImageView avatar = new ImageView(new Image(App.class.getResource(user.getAvatar()).toExternalForm()));
            avatar.setPreserveRatio(true);
            avatar.setFitHeight(36);
            if (user.getIsOnline()) {
                mainGrid.add(createLabel("online"), 0, i);
            }
            mainGrid.add(avatar, 1, i);
            mainGrid.add(createLabel(user.getNickname()), 2, i);
            mainGrid.add(createLabel(Integer.toString(user.getScore())), 3, i);
            mainGrid.add(createLabel(user.getLastWinTime()),
                    4, i);
            Label lastLoginTime = createLabel("Last Login: " + user.getLastLoginTime());
            lastLoginTime.setTextFill(Color.web(user.isCurrentUser() ? "#404040" : "#587189"));
            mainGrid.add(lastLoginTime, 5, i);

            Rectangle rectangle = new Rectangle(800, 40);
            rectangle.setArcWidth(20);
            rectangle.setArcHeight(10);
            rectangle.setFill(Color.web((user.isCurrentUser() ? "#4C779B" : "#023D6F"), 1));

            bgGrid.add(rectangle, 2, i++);
        }
    }
    private void getListFromServer(Response response){
//        Request request = new Request();
//        request.setHeader("getScoreBoard");
//        request.addData("jwt", User.getJwt());
//        Response response = NetworkController.send(request);
        Gson gson = new Gson();
        ArrayList<UserView> userViews = gson.fromJson((String) response.getData().get("scoreBoard"),
                new TypeToken<ArrayList<UserView>>() {
                }.getType());
        Platform.runLater(() -> addRatings(userViews));
    }

    public  void listenForUpdates() throws IOException {
        Socket readerSocket = new Socket("localhost", 5000);
        DataOutputStream outputStream = new DataOutputStream(readerSocket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(readerSocket.getInputStream());
        Request request = new Request();
        request.setHeader("getScoreBoard");
        request.addData("jwt", User.getJwt());
        outputStream.writeUTF(request.toJson());
        outputStream.flush();
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Waiting for update");
                    Response response = Response.fromJson(inputStream.readUTF());
                    System.out.println("update received");
                    this.getListFromServer(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
