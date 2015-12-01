package pl.gebickionline.webappforstudy.user;

import pl.gebickionline.webappforstudy.security.*;

import javax.inject.Inject;
import java.util.Optional;

public class UserManager {
    @Inject
    private UserContext context;

    @Inject
    private AuthenticationProvider authenticationProvider;

    public AuthToken authenticate(String login, String password) {
        AuthToken authToken = authenticationProvider.signIn(login, password);
        return authToken;
    }

    public void logout(AuthToken authToken) {
        authenticationProvider.signOut(authToken);
    }

    public Optional<UserDetails> userDetails() {
        if (context.loggedInUser().isPresent())
            return Optional.of(new UserDetails(context.loggedInUser().get().login()));

        return Optional.empty();
    }
}
