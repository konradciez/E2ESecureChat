import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class ChatScene {
    private Scene scene;
    private Map<String, List<String>> messages = new HashMap<>();
    private VBox chatArea = new VBox();
    private TextArea messageInput = new TextArea();
    private Label currentChatLabel = new Label();

    public ChatScene(Main app, User user) {
        Collection<User> registeredUsers = ServerData.getInstance().getAllUsers();
        ListView<String> userList = new ListView<>();

        for (User u : registeredUsers) {
            if (!u.getLogin().equals(user.getLogin())) {
                userList.getItems().add(u.getLogin());
            }
        }

        userList.setOnMouseClicked(e -> {
            String selectedUser = userList.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                showChat(selectedUser);
            }
        });

        Label welcomeLabel = new Label("Witaj, " + user.getLogin() + "!");
        welcomeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        VBox leftPane = new VBox(10, welcomeLabel, new Label("Konwersacje"), userList);
        leftPane.setPadding(new Insets(10));
        leftPane.setPrefWidth(180);

        messageInput.setPromptText("Napisz wiadomość...");
        messageInput.setPrefRowCount(2);

        Button sendButton = new Button("Wyślij");
        sendButton.setOnAction(e -> {
            String text = messageInput.getText();
            String currentUser = getCurrentUser();
            if (!text.isEmpty() && currentUser != null) {
                messages.get(currentUser).add("Ty: " + text);
                updateChatArea(currentUser);
                messageInput.clear();
            }
        });

        Button logoutButton = new Button("Wyloguj");
        logoutButton.setOnAction(e -> {
            UserService.getInstance().logout();
            app.showLoginScene();
        });

        VBox rightPane = new VBox(10);
        rightPane.setPadding(new Insets(10));
        chatArea.setSpacing(5);
        ScrollPane scrollPane = new ScrollPane(chatArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(300);

        rightPane.getChildren().addAll(currentChatLabel, scrollPane, messageInput, sendButton, logoutButton);

        BorderPane layout = new BorderPane();
        layout.setLeft(leftPane);
        layout.setCenter(rightPane);

        scene = new Scene(layout, 600, 400);
    }

    private void showChat(String user) {
        currentChatLabel.setText("Rozmowa z: " + user);
        messages.putIfAbsent(user, new ArrayList<>());
        updateChatArea(user);
    }

    private void updateChatArea(String user) {
        chatArea.getChildren().clear();
        for (String msg : messages.get(user)) {
            chatArea.getChildren().add(new Label(msg));
        }
    }

    private String getCurrentUser() {
        String labelText = currentChatLabel.getText();
        if (labelText != null && labelText.startsWith("Rozmowa z: ")) {
            return labelText.substring("Rozmowa z: ".length());
        }
        return null;
    }

    public Scene getScene() {
        return scene;
    }
}
