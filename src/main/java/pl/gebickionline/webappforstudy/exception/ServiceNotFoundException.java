package pl.gebickionline.webappforstudy.exception;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class ServiceNotFoundException extends ResourceNotFoundException {
    public ServiceNotFoundException(List<Integer> ids) {
        super("Nie znaleziono usług o identyfikatorach: " + ids.stream().map(Object::toString).collect(joining(", ")));
    }

    public ServiceNotFoundException(String message) {
        super(message);
    }
}
