package pl.gebickionline.webappforstudy.service.group;

import pl.gebickionline.webappforstudy.exception.BadRequestException;
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
public class ServiceGroupEndpoint {

    @Inject
    private ServiceGroupManager manager;

    @Path("admin/service/group")
    @POST
    public Response modifyGroupList(@Valid @NotNull(message = "Lista grup nie może być wartością NULL.") List<ServiceGroupDTO> request) {
        long distinctOrdinals = request.stream().map(g -> g.ordinal).distinct().count();

        if (distinctOrdinals < request.size())
            throw new BadRequestException("Numer porządkowy musi być unikatowy.");

        manager.updateGroupList(request);
        return Response.status(OK).build();
    }

    @Path("admin/service/group")
    @GET
    public Response getAll() {

        List<ServiceGroupDTO> response = manager.getAll();
        return Response.status(OK)
                .entity(response)
                .build();
    }

    @Path("service/group")
    @GET
    @Public
    public Response getAllVisible() {
        List<ServiceGroupDTO> response = manager.getVisible();
        return Response.status(OK)
                .entity(response)
                .build();
    }
}
