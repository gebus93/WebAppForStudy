package pl.gebickionline.webappforstudy.security;

import javax.ws.rs.NameBinding;
import java.lang.annotation.*;

@NameBinding
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Public {
}