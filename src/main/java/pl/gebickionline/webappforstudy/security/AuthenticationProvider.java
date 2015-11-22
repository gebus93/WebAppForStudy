package pl.gebickionline.webappforstudy.security;

import javax.ws.rs.core.MultivaluedMap;

public interface AuthenticationProvider {
    String APPLICATION_KEY = "1c226459-a721-4337-a7e6-f2864e0186b9";
    String APPLICATION_HEADER_KEY = "application";
    String AUTH_TOKEN_HEADER_KEY = "auth_token";

    void authenticateUser(MultivaluedMap<String, String> headers);

    AuthToken signIn(String login, String password);

    void signOut(AuthToken authToken);

}
