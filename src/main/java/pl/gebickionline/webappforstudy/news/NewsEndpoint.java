package pl.gebickionline.webappforstudy.news;

import org.json.JSONObject;
import pl.gebickionline.webappforstudy.security.Public;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;
import static pl.gebickionline.webappforstudy.news.NewsVisibility.VISIBLE;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class NewsEndpoint {
    @Inject
    private NewsManager newsManager;

    @Path("admin/news/visibility")
    @PUT
    public Response turnOn() {
        newsManager.turnOnNewsVisibility();
        return Response.status(OK).build();
    }

    @Path("admin/news/visibility")
    @DELETE
    public Response turnOff() {
        newsManager.turnOffNewsVisibility();
        return Response.status(OK).build();
    }

    @Path("news/visibility")
    @GET
    @Public
    public Response getVisibility() {
        NewsVisibility newsVisibility = newsManager.newsVisibility();

        String responseBody = new JSONObject()
                .put("visible", VISIBLE == newsVisibility)
                .toString();

        return Response.status(OK)
                .entity(responseBody)
                .build();
    }

    @Path("admin/news")
    @POST
    public Response addNews() {
        return Response.status(CREATED).build();
    }

    @Path("admin/news/{id}")
    @PUT
    public Response modifyNews(@PathParam("id") @Min(value = 0, message = "Identyfikator nie może być mniejszy od 0") Long newsId) {
        return Response.status(OK).build();
    }

    @Path("admin/news/{id}")
    @DELETE
    public Response deleteNews(@PathParam("id") @Min(value = 0, message = "Identyfikator nie może być mniejszy od 0") Long newsId) {
        return Response.noContent().build();
    }

    @Path("admin/news/list")
    @GET
    public Response getAll() {
        return Response.status(OK).build();
    }

    @Path("news")
    @GET
    @Public
    public Response getPage() {
        return Response.status(OK).build();
    }
}
