package pl.gebickionline.webappforstudy.news;

import pl.gebickionline.webappforstudy.configuration.ConfigurationManager;

import javax.inject.Inject;

public class NewsManager {
    public static final String NEWS_VISIBILITY = "newsVisibility";
    @Inject
    private ConfigurationManager configurationManager;

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
}
