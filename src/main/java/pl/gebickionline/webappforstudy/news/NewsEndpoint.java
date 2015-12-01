package pl.gebickionline.webappforstudy.news;

import org.json.*;
import pl.gebickionline.webappforstudy.security.Public;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.*;
import static pl.gebickionline.webappforstudy.news.NewsVisibility.VISIBLE;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class NewsEndpoint {
    @Inject
    private NewsManager newsManager;
    private Logger log = Logger.getLogger(NewsEndpoint.class.getName());

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
    public Response addNews(@Valid @NotNull(message = "Należy podać dane aktualności") NewsRequest request) {
        log.info(request.toString());
        long createdNewsId = newsManager.add(request.title, request.content);

        String responseBody = new JSONObject()
                .put("id", createdNewsId)
                .toString();

        return Response.status(CREATED)
                .entity(responseBody)
                .build();
    }

    @Path("admin/news/{id}")
    @PUT
    public Response modifyNews(
            @PathParam("id") @Min(value = 0, message = "Identyfikator nie może być mniejszy od 0") Long newsId,
            @Valid @NotNull(message = "Należy podać dane aktualności") NewsRequest request
    ) {
        log.info(String.format("Update news with id '%s'. New content: %s", newsId, request.toString()));
        newsManager.update(newsId, request.title, request.content);
        return Response.status(OK).build();
    }

    @Path("admin/news/{id}")
    @DELETE
    public Response deleteNews(@PathParam("id") @Min(value = 0, message = "Identyfikator nie może być mniejszy od 0") Long newsId) {
        newsManager.delete(newsId);
        return Response.noContent().build();
    }

    @Path("admin/news/list")
    @GET
    public Response getAll() {
        JSONArray response = new JSONArray();

        newsManager.getAllNews()
                .stream()
                .sorted(new NewsResponse())
                .map(n -> n.toJSONObject())
                .forEachOrdered(n -> response.put(n));

        return Response.status(OK).entity(response.toString()).build();
    }

    @Path("news/list")
    @GET
    @Public
    public Response getPage(
            @QueryParam("perPage") @DefaultValue("5") @Min(value = 1, message = "Ilość elementów na stronie musi być większa od 0.") Integer perPage,
            @QueryParam("page") @DefaultValue("0") @Min(value = 0, message = "Numer strony nie może być ujemny") Integer page
    ) {
        NewsPageResponse response = newsManager.getPage(page, perPage);
        return Response.status(OK).entity(response).build();
    }
}
