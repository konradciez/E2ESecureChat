import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoginScene {
    private Scene scene;

    public LoginScene(Main app) {
        TextField userField = new TextField();
        userField.setPromptText("Login");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Hasło");

        Label infoLabel = new Label();

        Button loginButton = new Button("Zaloguj się");
        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passwordField.getText();

            User user = ServerData.getInstance().getUserByLogin(username);
            if (user != null && password.equals("a")) { // !
                app.showProfileScene(user);
            } else {
                infoLabel.setText("Niepoprawne dane logowania.");
                infoLabel.setTextFill(Color.RED);
            }
        });

        Button registerButton = new Button("Zarejestruj się");
        registerButton.setOnAction(e -> app.showRegisterScene());

        VBox vBox = new VBox(10, userField, passwordField, loginButton, registerButton, infoLabel);
        vBox.setAlignment(Pos.CENTER);
        scene = new Scene(vBox, 300, 200);
    }

    public Scene getScene() {
        return scene;
    }
}
