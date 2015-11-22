package pl.gebickionline.webappforstudy.security;

import pl.gebickionline.webappforstudy.user.User;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "renewLastActivityTime", query = "UPDATE AuthenticationStorage SET lastActivityTime = CURRENT_TIMESTAMP WHERE authToken = :authToken"),
        @NamedQuery(name = "removeAuthentication", query = "DELETE FROM AuthenticationStorage WHERE authToken = :authToken"),
        @NamedQuery(name = "removeOldAuthentications", query = "DELETE FROM AuthenticationStorage WHERE lastActivityTime  < :boundaryTime"),
        @NamedQuery(name = "findAuthTokenByUser", query = "SELECT a.authToken FROM AuthenticationStorage a WHERE a.user = :user")
})
public class AuthenticationStorage {

    @Id
    @GeneratedValue
    private Integer id;


    @Column(nullable = false)
    private String authToken;

    @OneToOne
    private User user;

    @Column(nullable = false)
    private Date lastActivityTime;

    public AuthenticationStorage() {
    }

    public AuthenticationStorage(User user) {
        lastActivityTime = new Date();
        this.user = user;
        authToken = UUID.randomUUID().toString();
    }

    public Integer getId() {
        return id;
    }

    public String getAuthToken() {
        return authToken;
    }

    public User getUser() {
        return user;
    }

    public Date getLastActivityTime() {
        return lastActivityTime;
    }
}
