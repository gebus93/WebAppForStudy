package pl.gebickionline.webappforstudy.exception;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class ServiceGroupNotFoundException extends ResourceNotFoundException {
    public ServiceGroupNotFoundException(List<Integer> ids) {
        super("Nie znaleziono grup o identyfikatorach: " + ids.stream().map(Object::toString).collect(joining(", ")));
    }

    public ServiceGroupNotFoundException(String message) {
        super(message);
    }
}
