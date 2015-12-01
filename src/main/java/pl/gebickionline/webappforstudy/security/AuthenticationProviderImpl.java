package pl.gebickionline.webappforstudy.security;

import pl.gebickionline.webappforstudy.configuration.ConfigurationManager;
import pl.gebickionline.webappforstudy.exception.UnauthorizedException;
import pl.gebickionline.webappforstudy.user.*;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.core.MultivaluedMap;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Default
public class AuthenticationProviderImpl implements AuthenticationProvider, AuthenticationCleaner {
    @Inject
    private UserContext context;

    @Inject
    private PasswordProvider passwordProvider;

    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    @Inject
    private ConfigurationManager configurationManager;

    @Override
    @Transactional
    public void authenticateUser(MultivaluedMap<String, String> headers) {

        List<String> authTokenList = headers.get(AUTH_TOKEN_HEADER_KEY);
        if (authTokenList == null || authTokenList.size() != 1)
            throw new UnauthorizedException();

        String authToken = authTokenList.get(0);

        if (!context.isAuthTokenValid(authToken))
            throw new UnauthorizedException();

        em.createNamedQuery("renewLastActivityTime")
                .setParameter("authToken", authToken)
                .executeUpdate();

    }

    private Optional<User> getUserWithLogin(String login) {
        try {

            final User user = em.createNamedQuery("findUserByLogin", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
            return Optional.of(user);

        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public AuthToken signIn(String login, String password) {
        Optional<User> userOptional = getUserWithLogin(login);
        if (!userOptional.isPresent())
            throw new UnauthorizedException();

        User user = userOptional.get();

        if (!passwordProvider.checkPassword(password, user.password()))
            throw new UnauthorizedException();

        context.loggedInUser(user);

        final AuthenticationStorage authenticationStorage = new AuthenticationStorage(user);
        em.persist(authenticationStorage);
        return new AuthToken(authenticationStorage.authToken());
    }

    @Override
    @Transactional
    public void signOut(AuthToken authToken) {
        em.createNamedQuery("removeAuthentication")
                .setParameter("authToken", authToken.authToken())
                .executeUpdate();

        context.clear();
    }

    @Override
    @Transactional
    public void logoutAllInactiveUsers() {
        int sessionTimeout = configurationManager.getInt("sessionTimeout");
        long boundaryTime = new Date().getTime() - TimeUnit.MINUTES.toMillis(sessionTimeout);
        em.createNamedQuery("removeOldAuthentications")
                .setParameter("boundaryTime", new Date(boundaryTime))
                .executeUpdate();

    }
}
