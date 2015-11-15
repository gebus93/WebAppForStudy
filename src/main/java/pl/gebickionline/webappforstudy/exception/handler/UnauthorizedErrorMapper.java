package pl.gebickionline.webappforstudy.exception.handler;

import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.json.JSONArray;
import pl.gebickionline.webappforstudy.exception.UnauthorizedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;
import java.util.List;

@Provider
public class UnauthorizedErrorMapper implements ExceptionMapper<UnauthorizedException> {
    @Override
    public Response toResponse(UnauthorizedException e) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
