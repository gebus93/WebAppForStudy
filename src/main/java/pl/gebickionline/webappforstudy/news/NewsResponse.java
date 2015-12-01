package pl.gebickionline.webappforstudy.news;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class NewsResponse implements Comparator<NewsResponse> {
    private final Long id;
    private final String title;
    private final String content;
    private final String creationTime;
    private final String lastUpdateTime;

    public NewsResponse() {
        id = null;
        title = null;
        content = null;
        creationTime = null;
        lastUpdateTime = null;
    }

    public String getContent() {
        return content;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public Long getId() {
        return id;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public String getTitle() {
        return title;
    }

    public NewsResponse(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.content = builder.content;
        this.creationTime = builder.creationTime;
        this.lastUpdateTime = builder.lastUpdateTime;
    }

    public JSONObject toJSONObject() {
        return new JSONObject()
                .put("id", id)
                .put("title", title)
                .put("content", content)
                .put("creationTime", creationTime)
                .put("lastUpdateTime", lastUpdateTime);
    }

    @Override
    public int compare(NewsResponse o1, NewsResponse o2) {
        return o1.creationTime.compareTo(o2.creationTime);
    }

    public static class Builder {
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmm");
        private String content;
        private Long id;
        private String title;
        private String creationTime;
        private String lastUpdateTime;

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withCreationTime(Date creationTime) {
            this.creationTime = creationTime == null ? null : sdf.format(creationTime);
            return this;
        }

        public Builder withLastUpdateTime(Date lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime == null ? null : sdf.format(lastUpdateTime);
            ;
            return this;
        }

        public NewsResponse build() {
            return new NewsResponse(this);
        }
    }
}
