package pl.gebickionline.webappforstudy.pages;

import org.json.JSONObject;
import pl.gebickionline.webappforstudy.security.Public;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.*;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class ContactDataEndpoint {

    @Inject
    private PagesManager pagesManager;

    @POST
    @Path("admin/contact")
    public Response modifyContactData(@Valid @NotNull(message = "Należy zdefiniować zawartość strony") ContactDataRequest request) {
        pagesManager.changePageContent("contact", request.content);
        return Response.status(OK).build();
    }

    @GET
    @Public
    @Path("contact")
    public Response getContactData() {
        Optional<String> pageContent = pagesManager.getPageContent("contact");
        if (pageContent.isPresent())
            return Response.status(OK)
                    .entity(new JSONObject().put("content", pageContent.get()).toString())
                    .build();

        return Response.status(NOT_FOUND).build();
    }
}
