package pl.gebickionline.webappforstudy.pages;

import org.hibernate.validator.constraints.NotBlank;

public class ContactDataRequest {
    @NotBlank(message = "Treść strony nie może być pusta")
    public String content;
}
