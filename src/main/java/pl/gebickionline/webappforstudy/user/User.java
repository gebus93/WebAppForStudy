package pl.gebickionline.webappforstudy.user;

import javax.persistence.*;

@Entity
@Table(name = "users_details")
@NamedQuery(name = "findUserByLogin", query = "SELECT u FROM User u WHERE u.login = :login")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 32)
    private String login;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Integer id() {
        return id;
    }

    public String login() {
        return login;
    }

    public String password() {
        return password;
    }
}
