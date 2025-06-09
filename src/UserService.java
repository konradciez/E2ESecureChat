import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static UserService instance;
    private User currentUser;
    private final Map<String, User> userMap = new HashMap<>();

    private UserService() {}

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void login(User user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void registerUser(User user) {
        userMap.put(user.getLogin(), user);
    }

    public User getUserByLogin(String login) {
        return userMap.get(login);
    }
}