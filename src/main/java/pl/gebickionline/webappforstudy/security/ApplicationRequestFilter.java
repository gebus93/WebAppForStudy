package pl.gebickionline.webappforstudy.security;

import pl.gebickionline.webappforstudy.exception.UnauthorizedException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.*;
import java.io.IOException;
import java.util.List;

import static pl.gebickionline.webappforstudy.security.AuthenticationProvider.APPLICATION_HEADER_KEY;
import static pl.gebickionline.webappforstudy.security.AuthenticationProvider.APPLICATION_KEY;

@Priority(Priorities.AUTHENTICATION)
public class ApplicationRequestFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext context) throws IOException {

        List<String> applicationKeyList = context.getHeaders().get(APPLICATION_HEADER_KEY);
        if (applicationKeyList == null || applicationKeyList.size() != 1 || !APPLICATION_KEY.equals(applicationKeyList.get(0)))
            throw new UnauthorizedException();

    }
}
