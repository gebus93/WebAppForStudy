package pl.gebickionline.webappforstudy.contact;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Łukasz on 2015-11-15.
 */
public class ContactDataRequest {
    @NotBlank(message = "Treść podstrony nie może być pusta")
    public String content;
}
