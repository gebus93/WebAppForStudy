package pl.gebickionline.webappforstudy.exception.handler;

import org.json.*;
import pl.gebickionline.webappforstudy.exception.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {
    @Override
    public Response toResponse(BadRequestException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(
                        new JSONObject()
                                .put("errors", new JSONArray()
                                                .put(0, e.getMessage())
                                ).toString()
                ).build();
    }
}
