package pl.gebickionline.webappforstudy.configuration;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Optional;

@Stateless
public class ConfigurationManager {

    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key).orElse("false"));
    }

    public int getInt(String key) {
        return Integer.parseInt(get(key).orElse("0"));
    }

    public String getString(String key) {
        return get(key).orElse("");
    }

    public void changeParam(String key, Object newParamValue) {
        Configuration configuration = em.createNamedQuery("Configuration.findByName", Configuration.class)
                .setParameter("name", key)
                .getSingleResult();

        configuration.value(String.valueOf(newParamValue));
        em.merge(configuration);
    }

    private Optional<String> get(String key) {
        try {
            Configuration configuration = em.createNamedQuery("Configuration.findByName", Configuration.class)
                    .setParameter("name", key)
                    .getSingleResult();
            return Optional.ofNullable(configuration.value());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
