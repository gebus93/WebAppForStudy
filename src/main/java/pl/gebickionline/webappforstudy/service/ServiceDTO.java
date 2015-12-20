package pl.gebickionline.webappforstudy.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceDTO {
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
