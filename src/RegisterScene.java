import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class RegisterScene {
    private Scene scene;

    public RegisterScene(Main app) {
        TextField userField = new TextField();
        userField.setPromptText("Login");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Hasło");

        Label infoLabel = new Label();

        Button registerButton = new Button("Zarejestruj się");
        registerButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passwordField.getText();

            if (!username.isEmpty() && !password.isEmpty()) {
                try {
                    String[] keys = KeyGeneratorUtil.generateKeyPairBase64();

                    User newUser = new User(username.hashCode(), username, keys[0]);
                    KeyStorageUtil.savePrivateKey(keys[1], username);

                    ServerData.getInstance().registerUser(newUser); //narazie rejestrujemy użytkownika w lokalnej klasie serwer-like

                    infoLabel.setText("Rejestracja przeszła pomyślnie.");
                    infoLabel.setTextFill(Color.GREEN);

                } catch (Exception ex) {
                    infoLabel.setText("Błąd przy zarządzaniu kluczami.");
                    infoLabel.setTextFill(Color.RED);
                }

            } else {
                infoLabel.setText("Wypełnij wszystkie pola.");
                infoLabel.setTextFill(Color.RED);
            }
        });

        Button backButton = new Button("Powrót do logowania");
        backButton.setOnAction(e -> app.showLoginScene());

        VBox layout = new VBox(10, userField, passwordField, registerButton, backButton, infoLabel);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 300, 250);
    }

    public Scene getScene() {
        return scene;
    }
}