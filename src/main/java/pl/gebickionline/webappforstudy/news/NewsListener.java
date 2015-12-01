package pl.gebickionline.webappforstudy.news;

import javax.persistence.*;
import java.util.Date;

public class NewsListener {
    @PrePersist
    public void prePersist(News news) {
        Date date = new Date();
        news.creationTime(date);
        news.lastUpdateTime(date);
    }

    @PreUpdate
    public void preUpdate(News news) {
        news.lastUpdateTime(new Date());
    }
}
