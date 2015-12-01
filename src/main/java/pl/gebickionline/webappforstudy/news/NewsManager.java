package pl.gebickionline.webappforstudy.news;

import org.jetbrains.annotations.NotNull;
import pl.gebickionline.webappforstudy.configuration.ConfigurationManager;
import pl.gebickionline.webappforstudy.exception.NewsNotFoundException;

import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class NewsManager {
    public static final String NEWS_VISIBILITY = "newsVisibility";
    @Inject
    private ConfigurationManager configurationManager;

    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    public void turnOnNewsVisibility() {
        configurationManager.changeParam(NEWS_VISIBILITY, true);
    }

    public void turnOffNewsVisibility() {
        configurationManager.changeParam(NEWS_VISIBILITY, false);
    }

    public NewsVisibility newsVisibility() {
        if (configurationManager.getBoolean(NEWS_VISIBILITY))
            return NewsVisibility.VISIBLE;

        return NewsVisibility.INVISIBLE;
    }

    public long add(@NotNull String title, @NotNull String content) {
        final News news = new News()
                .title(title)
                .content(content);

        em.persist(news);

        return news.id();
    }

    public void update(@NotNull Long newsId, @NotNull String title, @NotNull String content) {
        News news = getNews(newsId);
        em.merge(news.title(title).content(content));
    }

    public void delete(Long newsId) {
        News news = getNews(newsId);
        em.remove(news);
    }

    private News getNews(Long newsId) {
        try {
            return em.createNamedQuery("News.findById", News.class)
                    .setParameter("newsId", newsId)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new NewsNotFoundException();
        }
    }

    public List<NewsResponse> getAllNews() {
        List<News> list = em.createNamedQuery("News.findAll", News.class).getResultList();

        return list.stream()
                .map(n -> new NewsResponse.Builder()
                        .withId(n.id())
                        .withTitle(n.title())
                        .withContent(n.content())
                        .withCreationTime(n.creationTime())
                        .withLastUpdateTime(n.lastUpdateTime())
                        .build())
                .collect(Collectors.toList());
    }

    public NewsPageResponse getPage(@NotNull Integer page, @NotNull Integer perPage) {
        long newsCount = em.createNamedQuery("News.count", Long.class).getSingleResult();

        List<News> list = em.createNamedQuery("News.findAll", News.class)
                .setFirstResult(page * perPage)
                .setMaxResults(perPage)
                .getResultList();

        List<NewsResponse> news = list.stream()
                .map(n -> new NewsResponse.Builder()
                        .withId(n.id())
                        .withTitle(n.title())
                        .withContent(n.content())
                        .withCreationTime(n.creationTime())
                        .withLastUpdateTime(n.lastUpdateTime())
                        .build())
                .collect(Collectors.toList());

        double pages = Math.ceil((double) newsCount / perPage);
        return new NewsPageResponse(news, (long) pages);
    }
}
