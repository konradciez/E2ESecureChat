import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ServerData {
    private static ServerData instance;
    private Map<String, User> users = new HashMap<>();

    private ServerData() {}

    public static ServerData getInstance() {
        if (instance == null) {
            instance = new ServerData();
        }
        return instance;
    }

    public void registerUser(User user) {
        users.put(user.getLogin(), user);
    }

    public User getUserByLogin(String login) {
        return users.get(login);
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }
}
