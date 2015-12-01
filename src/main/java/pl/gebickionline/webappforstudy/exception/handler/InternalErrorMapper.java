package pl.gebickionline.webappforstudy.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;

@Provider
public class InternalErrorMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException e) {
        e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
