package pl.gebickionline.webappforstudy.service.group;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class ServiceGroupDTO {
    public Integer id;
    @NotNull(message = "Numer porządkowy jest wymagany")
    public Integer ordinal;
    @NotBlank(message = "Nazwa grupy jest wymagana")
    public String name;
    @NotNull(message = "Status widoczności grupy jest wymagany")
    public Boolean visible;
}
