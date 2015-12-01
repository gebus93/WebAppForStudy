package pl.gebickionline.webappforstudy.news;


import java.util.*;

public class NewsPageResponse {
    private Long pages;
    private List<NewsResponse> news;

    public NewsPageResponse(List<NewsResponse> news, Long pages) {
        setNews(news);
        setPages(pages);
    }

    public List<NewsResponse> getNews() {
        return news;
    }

    public void setNews(List<NewsResponse> news) {
        this.news = news == null ? Collections.emptyList() : news;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages == null ? 0 : pages;
    }
}
