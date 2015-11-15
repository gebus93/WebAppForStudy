package pl.gebickionline.webappforstudy.contact;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Łukasz on 2015-11-15.
 */
@Path("/")
public class ContactDataEndpoint {
    @POST
    @Path("admin/contact")
    public Response modifyContactData(@Valid ContactDataRequest request) {
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("contact")
    public Response getContactData() {
        return Response.status(Response.Status.OK)
                .entity("{\"content\":\"\"}")
                .build();
    }
}
