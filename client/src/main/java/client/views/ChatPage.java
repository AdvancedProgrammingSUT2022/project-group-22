package client.views;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ChatPage extends Menu {
    public ChatPage() {
        Stage chatStage = new Stage();
        chatStage.setTitle("chat");
        Scene scene = new Scene(createPage(), 300, 600, Color.web("#ADD8E6"));
        chatStage.setScene(scene);
        chatStage.show();
    }

    public BorderPane createPage() {
        BorderPane pane = new BorderPane();
        TextFlow chatContent = new TextFlow();
        Text text = new Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                        "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation" +
                        "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in" +
                        "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
                        "cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        text.setFill(Color.BLUE);
        chatContent.setMaxWidth(200);
        chatContent.getChildren().add(text);

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(chatContent);
        pane.setCenter(scroll);

        TextArea textBox = new TextArea();
        textBox.setPromptText("Message...");
        pane.setBottom(textBox);

        return pane;
    }
}
