package pl.gebickionline.webappforstudy.errorhandler;

import org.jboss.resteasy.api.validation.*;
import org.json.JSONArray;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;
import java.util.List;

/**
 * Created by Łukasz on 2015-11-08.
 */
@Provider
public class ValidationErrorMapper implements ExceptionMapper<ResteasyViolationException> {
    @Override
    public Response toResponse(ResteasyViolationException e) {
        List<ResteasyConstraintViolation> violations = e.getViolations();
        JSONArray errors = new JSONArray();
        int i = 0;
        for (ResteasyConstraintViolation c : violations) {
            String message = c.getMessage();
            errors.put(i++, message);
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errors.toString())
                .header("content-type", "application/json;charset=UTF-8")
                .build();
    }
}
