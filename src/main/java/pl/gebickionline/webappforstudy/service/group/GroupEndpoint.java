package pl.gebickionline.webappforstudy.service.group;

import pl.gebickionline.webappforstudy.security.Public;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.OK;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class GroupEndpoint {

    @Path("admin/service/group")
    @POST
    public Response modifyGroupList(@Valid List<GroupRequest> request) {
        return Response.status(OK).build();
    }

    @Path("admin/service/group")
    @GET
    public Response getAll() {
        return Response.status(OK).build();
    }

    @Path("service/group")
    @GET
    @Public
    public Response getAllVisible() {
        return Response.status(OK).build();
    }
}
