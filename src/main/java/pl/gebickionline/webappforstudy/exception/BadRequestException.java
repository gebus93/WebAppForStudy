package pl.gebickionline.webappforstudy.exception;

/**
 * Created by Łukasz on 2016-01-25.
 */
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
