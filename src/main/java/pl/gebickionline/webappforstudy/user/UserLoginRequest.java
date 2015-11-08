package pl.gebickionline.webappforstudy.user;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Łukasz on 2015-11-08.
 */
public class UserLoginRequest {
    @NotBlank(message = "Należy podać login")
    public String login;
    @NotBlank(message = "Należy podać hasło")
    public String password;
}
