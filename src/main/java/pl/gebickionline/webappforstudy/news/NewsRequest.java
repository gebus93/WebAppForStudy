package pl.gebickionline.webappforstudy.news;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class NewsRequest {
    @NotBlank(message = "Należy podać tytuł")
    @Size(max = 50, message = "Tytuł nie może być dłuższy niż 50 znaków")
    public String title;

    @NotBlank(message = "Należy podać treść")
    public String content;

    @Override
    public String toString() {
        return "NewsRequest{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
