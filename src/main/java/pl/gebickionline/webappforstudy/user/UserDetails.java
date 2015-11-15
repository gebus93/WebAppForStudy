package pl.gebickionline.webappforstudy.user;

public class UserDetails {
    private String login;

    private UserDetails() {
    }

    public UserDetails(String login) {
        this.login = login;
    }

    public String login() {
        return login;
    }
}
