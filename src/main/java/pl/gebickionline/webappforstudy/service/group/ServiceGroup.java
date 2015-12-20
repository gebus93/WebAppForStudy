package pl.gebickionline.webappforstudy.service.group;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "ServiceGroup.findAll", query = "SELECT g FROM ServiceGroup g"),
        @NamedQuery(name = "ServiceGroup.findByID", query = "SELECT g FROM ServiceGroup g WHERE g.id = :id"),
        @NamedQuery(name = "ServiceGroup.removeServicesFromGroup", query = "DELETE FROM Service s WHERE s.group = :group")
})
public class ServiceGroup {
    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer ordinal;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Boolean visible;

    public Integer id() {
        return id;
    }

    public ServiceGroup id(Integer id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public ServiceGroup name(String name) {
        this.name = name;
        return this;
    }

    public Integer ordinal() {
        return ordinal;
    }

    public ServiceGroup ordinal(Integer ordinal) {
        this.ordinal = ordinal;
        return this;
    }

    public Boolean visible() {
        return visible;
    }

    public ServiceGroup visible(Boolean visible) {
        this.visible = visible;
        return this;
    }
}
