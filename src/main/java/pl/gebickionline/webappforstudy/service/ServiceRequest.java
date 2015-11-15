package pl.gebickionline.webappforstudy.service;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Łukasz on 2015-11-15.
 */
public class ServiceRequest {
    public Integer id;
    @NotNull(message = "Numer porządkowy jest wymagany")
    public Integer ordinal;
    @NotBlank(message = "Nazwa usługi jest wymagana")
    public String name;
    public Integer price;
    public Integer minPrice;
    public Integer maxPrice;
    @NotNull(message = "Status widoczności usługi jest wymagany")
    public Boolean visible;
    @NotNull(message = "Numer grupy jest wymagany")
    public Integer groupID;
}
