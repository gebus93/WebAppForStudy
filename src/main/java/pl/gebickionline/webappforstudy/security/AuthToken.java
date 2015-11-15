package pl.gebickionline.webappforstudy.security;

public class AuthToken {
    private final String authToken;

    public AuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String authToken() {
        return authToken;
    }
}
