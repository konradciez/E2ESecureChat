import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;

public class ChatScene {
    private Scene scene;

    public ChatScene(Main app, User user) {
        Collection<User> registeredUsers = ServerData.getInstance().getAllUsers();
        ListView<String> userList = new ListView<>();

        for (User u : registeredUsers) {
            if (!u.getLogin().equals(user.getLogin())) {
                userList.getItems().add(u.getLogin());
            }
        }

        Label welcomeLabel = new Label("Witaj, " + user.getLogin() + "!");

        VBox leftPane = new VBox(10, welcomeLabel, new Label("Lista użytkowników:"), userList);

        TextArea messageInput = new TextArea();
        messageInput.setPromptText("Napisz wiadomość...");

        Button sendButton = new Button("Wyślij");
        sendButton.setOnAction(e -> {
            messageInput.clear();
        });

        Button logoutButton = new Button("Wyloguj");
        logoutButton.setOnAction(e -> {
            app.showLoginScene();
        });

        VBox rightPane = new VBox(10);

        rightPane.getChildren().addAll(messageInput, sendButton, logoutButton);

        BorderPane pane = new BorderPane();
        pane.setLeft(leftPane);
        pane.setCenter(rightPane);

        scene = new Scene(pane, 600, 400);
    }

    public Scene getScene() {
        return scene;
    }
}
