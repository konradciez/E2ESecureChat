public class User {
    private final int id;
    private final String login;
    private final String publicKey;

    public User(int id, String login, String publicKey) {
        this.id = id;
        this.login = login;
        this.publicKey = publicKey;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
