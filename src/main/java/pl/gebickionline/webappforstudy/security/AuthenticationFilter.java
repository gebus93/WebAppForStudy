package pl.gebickionline.webappforstudy.security;

import pl.gebickionline.webappforstudy.exception.UnauthorizedException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.*;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.List;

import static pl.gebickionline.webappforstudy.security.AuthenticationProvider.*;

@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    AuthenticationProvider authenticationProvider;

    public AuthenticationFilter(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        authenticationProvider.authenticateUser(requestContext.getHeaders());
    }
}