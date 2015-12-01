package pl.gebickionline.webappforstudy.pages;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "findPageByName", query = "SELECT p FROM Page p WHERE p.name = :pageName")
})
@Table(name = "pages")
public class Page {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 65535)
    private String content;

    public Integer id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String content() {
        return content;
    }

    public Page content(@NotNull String content) {
        this.content = content;
        return this;
    }
}
