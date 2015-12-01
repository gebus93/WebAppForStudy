package pl.gebickionline.webappforstudy.configuration;

import javax.persistence.*;


@Entity
@NamedQueries({
        @NamedQuery(name = "Configuration.findByName", query = "SELECT c FROM Configuration c WHERE c.configurationParam = :name")
})
public class Configuration {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 255)
    private String configurationParam;

    @Column(nullable = false, length = 255)
    private String configurationValue;

    public String paramName() {
        return configurationParam;
    }

    public String value() {
        return configurationValue;
    }

    public void paramName(String configurationParam) {
        this.configurationParam = configurationParam;
    }

    public void value(String configurationValue) {
        this.configurationValue = configurationValue;
    }
}
