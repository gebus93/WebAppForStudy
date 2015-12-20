package pl.gebickionline.webappforstudy.service;

import pl.gebickionline.webappforstudy.service.group.ServiceGroup;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")
})
public class Service {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private ServiceGroup group;

    @Column(nullable = false)
    private Integer ordinal;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Boolean visible;

    private Integer price;
    private Integer minPrice;
    private Integer maxPrice;


    public Integer id() {
        return id;
    }

    public Service id(Integer id) {
        this.id = id;
        return this;
    }

    public ServiceGroup group() {
        return group;
    }

    public Service group(ServiceGroup group) {
        this.group = group;
        return this;
    }

    public String name() {
        return name;
    }

    public Service name(String name) {
        this.name = name;
        return this;
    }

    public Integer ordinal() {
        return ordinal;
    }

    public Service ordinal(Integer ordinal) {
        this.ordinal = ordinal;
        return this;
    }

    public Boolean visible() {
        return visible;
    }

    public Service visible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Integer maxPrice() {
        return maxPrice;
    }

    public Service maxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public Integer minPrice() {
        return minPrice;
    }

    public Service minPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer price() {
        return price;
    }

    public Service price(Integer price) {
        this.price = price;
        return this;
    }
}
