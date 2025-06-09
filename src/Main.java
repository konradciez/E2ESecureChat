import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        showLoginScene();
        primaryStage.setTitle("E2E Secure");
        primaryStage.show();
    }

    public void showLoginScene() {
        primaryStage.setScene(new LoginScene(this).getScene());
    }

    public void showRegisterScene() {
        primaryStage.setScene(new RegisterScene(this).getScene());
    }

    public void showProfileScene(User user) {
        UserService.getInstance().login(user);
        primaryStage.setScene(new ChatScene(this, user).getScene());
    }
}
