package pl.gebickionline.webappforstudy.user;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@SessionScoped
public class UserContext implements Serializable {

    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    private Optional<User> loggedInUser;

    @PostConstruct
    public void init() {
        loggedInUser = Optional.empty();
    }

    public void loggedInUser(User user) {
        this.loggedInUser = Optional.ofNullable(user);
    }

    public Optional<User> loggedInUser() {
        return loggedInUser;
    }

    public void clear() {
        loggedInUser = Optional.empty();
    }

    public boolean isAuthTokenValid(String authToken) {
        if (!loggedInUser.isPresent())
            return false;

        try {
            String realAuthToken = em.createNamedQuery("findAuthTokenByUser", String.class)
                    .setParameter("user", loggedInUser.get())
                    .getSingleResult();

            return realAuthToken.equals(authToken);
        } catch (NoResultException e) {
            clear();
            return false;
        }
    }
}
