package pl.gebickionline.webappforstudy.exception.handler;

import org.json.*;
import pl.gebickionline.webappforstudy.exception.ResourceNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;

@Provider
public class ResourceNotFoundErrorMapper implements ExceptionMapper<ResourceNotFoundException> {
    @Override
    public Response toResponse(ResourceNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(
                        new JSONObject()
                                .put("errors", new JSONArray()
                                                .put(0, e.getMessage())
                                ).toString()
                ).build();
    }
}
