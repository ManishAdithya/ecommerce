
public class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getpassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User Name: " + name + ", Email: " + password;
    }
}

