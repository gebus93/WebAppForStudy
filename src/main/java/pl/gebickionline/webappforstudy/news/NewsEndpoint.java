package pl.gebickionline.webappforstudy.news;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;

/**
 * Created by �ukasz on 2015-11-07.
 */
@Path("/")
public class NewsEndpoint {
    @Path("admin/news")
    @PUT
    public Response turnOn() {
        return Response.status(OK).build();
    }

    @Path("admin/news")
    @DELETE
    public Response turnOff() {
        return Response.status(OK).build();
    }

    @Path("news")
    @GET
    public Response getVisibility() {
        return Response.status(OK).build();
    }

    @Path("admin/news")
    @POST
    public Response addNews() {
        return Response.status(CREATED).build();
    }

    @Path("admin/news")
    @PUT
    public Response modifyNews() {
        return Response.status(OK).build();
    }

    @Path("admin/news")
    @DELETE
    public Response deleteNews() {
        return Response.noContent().build();
    }

    @Path("admin/news")
    @GET
    public Response getAll() {
        return Response.status(OK).build();
    }

    @Path("news")
    @GET
    public Response getPage() {
        return Response.status(OK).build();
    }
}
