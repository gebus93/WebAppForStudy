package pl.gebickionline.webappforstudy.pages;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Logger;

@Transactional
public class PagesManager {

    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    private Logger logger = Logger.getLogger(PagesManager.class.getName());

    public Optional<String> getPageContent(String pageName) {
        final Optional<Page> page = findPage(pageName);

        if (page.isPresent())
            return Optional.of(page.get().content());

        return Optional.empty();
    }

    private Optional<Page> findPage(String pageName) {
        try {
            Page page = em.createNamedQuery("findPageByName", Page.class)
                    .setParameter("pageName", pageName)
                    .getSingleResult();
            return Optional.of(page);
        } catch (NoResultException ex) {
            logger.warning(String.format("Page %s does not exist.", pageName));
        }
        return Optional.empty();
    }

    public boolean changePageContent(String pageName, String newContent) {
        final Optional<Page> page = findPage(pageName);

        if (!page.isPresent())
            return false;

        em.merge(page.get().content(newContent));
        return true;
    }
}
