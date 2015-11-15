package pl.gebickionline.webappforstudy.service.group;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.OK;

/**
 * Created by �ukasz on 2015-11-07.
 */
@Path("/")
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
    public Response getAllVisible() {
        return Response.status(OK).build();
    }
}
