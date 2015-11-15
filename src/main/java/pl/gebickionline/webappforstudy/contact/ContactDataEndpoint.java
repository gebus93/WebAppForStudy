package pl.gebickionline.webappforstudy.contact;

import pl.gebickionline.webappforstudy.security.Public;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/")
public class ContactDataEndpoint {
    @POST
    @Path("admin/contact")
    public Response modifyContactData(@Valid ContactDataRequest request) {
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Public
    @Path("contact")
    public Response getContactData() {
        return Response.status(Response.Status.OK)
                .entity("{\"content\":\"\"}")
                .build();
    }
}
