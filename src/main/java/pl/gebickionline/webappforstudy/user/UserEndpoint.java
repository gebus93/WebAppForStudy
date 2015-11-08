package pl.gebickionline.webappforstudy.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * Created by Łukasz on 2015-11-07.
 */
@Path("/user")
@Consumes("application/json")
@Produces("application/json")
public class UserEndpoint {

    Logger logger = Logger.getLogger(UserEndpoint.class.getName());

    @POST
    @Path("login")
    public Response login(@NotNull(message = "Należy podać dane logowania") @Valid UserLoginRequest request) {
        logger.info("login: " + request.login);
        logger.info("hasło: " + request.password);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("logout")
    public Response logout() {
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public Response userDetails() {
        String responseBody = "{\"login\": \"Admin\"}";
        return Response.status(Response.Status.OK)
                .entity(responseBody)
                .build();
    }
}
