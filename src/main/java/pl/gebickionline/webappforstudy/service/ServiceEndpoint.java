package pl.gebickionline.webappforstudy.service;

import pl.gebickionline.webappforstudy.security.Public;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.OK;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class ServiceEndpoint {

    @Inject
    private ServiceManager manager;

    @Path("admin/service")
    @POST
    public Response modifyServiceList(@Valid @NotNull(message = "Lista usług nie może być wartością null") List<ServiceDTO> request) {
        manager.updateServiceList(request);
        return Response.status(OK).build();
    }

    @Path("admin/service")
    @GET
    public Response getAll() {
        List<ServiceDTO> response = manager.getAll();
        return Response.status(OK)
                .entity(response)
                .build();
    }

    @Path("service")
    @GET
    @Public
    public Response getAllVisible() {
        List<ServiceDTO> response = manager.getVisible();
        return Response.status(OK)
                .entity(response)
                .build();
    }
}
