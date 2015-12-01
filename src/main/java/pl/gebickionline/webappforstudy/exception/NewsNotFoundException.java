package pl.gebickionline.webappforstudy.exception;

public class NewsNotFoundException extends ResourceNotFoundException {
    public NewsNotFoundException() {
        super("Wpis o wskazanym identyfikatorze nie istnieje.");
    }
}
