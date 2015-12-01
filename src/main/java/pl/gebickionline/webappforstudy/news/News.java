package pl.gebickionline.webappforstudy.news;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners({NewsListener.class})
@NamedQueries({
        @NamedQuery(name = "News.findById", query = "SELECT n FROM News n WHERE n.id = :newsId"),
        @NamedQuery(name = "News.findAll", query = "SELECT n FROM News n"),
        @NamedQuery(name = "News.count", query = "SELECT COUNT(n) FROM News n")
})
public class News {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Date creationTime;
    private Date lastUpdateTime;

    public long id() {
        return id;
    }

    public News title(String title) {
        this.title = title;
        return this;
    }

    public News content(String content) {
        this.content = content;
        return this;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public Date creationTime() {
        return creationTime;
    }

    public Date lastUpdateTime() {
        return lastUpdateTime;
    }

    void creationTime(Date date) {
        creationTime = date;
    }

    void lastUpdateTime(Date date) {
        lastUpdateTime = date;
    }
}
