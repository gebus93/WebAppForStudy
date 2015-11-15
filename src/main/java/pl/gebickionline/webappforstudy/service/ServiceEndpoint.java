package pl.gebickionline.webappforstudy.service;

import pl.gebickionline.webappforstudy.security.Public;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.OK;

@Path("/")
public class ServiceEndpoint {

    @Path("admin/service")
    @POST
    public Response modifyServiceList(@Valid List<ServiceRequest> request) {
        return Response.status(OK).build();
    }

    @Path("admin/service")
    @GET
    public Response getAll() {
        return Response.status(OK).build();
    }

    @Path("service")
    @GET
    @Public
    public Response getAllVisible() {
        return Response.status(OK).build();
    }
}
