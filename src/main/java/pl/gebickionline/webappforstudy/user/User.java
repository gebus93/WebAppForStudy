package pl.gebickionline.webappforstudy.user;

import javax.persistence.*;

/**
 * Created by Łukasz on 2015-11-15.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 32)
    private String login;

    @Column(nullable = false)
    private String password;

    private User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
